package com.favorite.pecodetest.view

import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.runtime.*
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.AndroidViewModel
import com.favorite.pecodetest.MainActivity

class MainActivityViewModel(val context: Application, val CHANNEL_ID: String) :
    AndroidViewModel(context) {

    var numberOfFragments by mutableStateOf(1)
    var visible by mutableStateOf(numberOfFragments > 1)

    fun addElement() {
        numberOfFragments++
    }

    fun deleteElement() {
        removeNotification()
        numberOfFragments--
    }

    private fun removeNotification() {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(numberOfFragments)
    }

    fun createNotification(title: String, description: String, id: Int) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
        }

        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.putExtra(CHANNEL_ID, id)

        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(
                context,
                0,
                intent,
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
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