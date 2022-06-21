package com.favorite.pecodetest.view

import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.compose.runtime.*
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.AndroidViewModel
import com.favorite.pecodetest.MainActivity
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivityViewModel(private val context: Application, private val CHANNEL_ID: String) :
    AndroidViewModel(context) {
    private val KEY: String = "key"
    private val sharedPref: SharedPreferences = context.getSharedPreferences(KEY, Context.MODE_PRIVATE)
    var numberOfFragments by mutableStateOf(1)
    var visible by mutableStateOf(numberOfFragments > 1)
    var page = MutableStateFlow(-1)

    init {
        numberOfFragments = sharedPref.getInt(KEY, 1)
    }

    fun addElement() {
        numberOfFragments++
    }

    fun deleteElement() {
        removeNotification()
        numberOfFragments--
    }

    fun saveElements() {
        with (sharedPref.edit()) {
            putInt(KEY, numberOfFragments)
            apply()
        }
    }

    fun getSavedElements(intent: Intent) {
        page.value = intent.getIntExtra(CHANNEL_ID, -1)
    }



    private fun removeNotification() {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(numberOfFragments - 1)
    }

    fun createNotification(title: String, description: String, id: Int) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        intent.putExtra(CHANNEL_ID, id)

        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(
                context,
                id,
                intent,
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    PendingIntent.FLAG_IMMUTABLE
                } else {
                    PendingIntent.FLAG_UPDATE_CURRENT
                }
            )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(com.favorite.pecodetest.R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(description)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            notify(id, builder.build())
        }
    }
}