package com.favorite.pecodetest.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.favorite.pecodetest.MainActivity
import com.favorite.pecodetest.R

class MainViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    val CHANNEL_ID: String = "CHANNEL_ID"


    init {
        Log.d("LOL", "LOL")
       // createNotificationChannel()
    }

//    private fun createNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name = "Pecode notification channel"
//            val descriptionText = "Testing notifications"
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
//                description = descriptionText
//            }
//
//            val notificationManager: NotificationManager =
//                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }
//
//    private fun createNotificationBuilder() {
//        val intent = Intent(context, MainActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//
//        val pendingIntent: PendingIntent =
//            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
//
//        var builder = NotificationCompat.Builder(context, "CHANNEL_ID")
//            .setContentTitle("You have crated a notification")
//            .setContentText("Notification ")
//            .setContentIntent(pendingIntent)
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//    }
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel() as T
    }
}