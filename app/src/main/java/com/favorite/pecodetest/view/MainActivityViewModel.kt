package com.favorite.pecodetest.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.provider.Settings.Global.getString
import androidx.compose.runtime.*
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    var numberOfFragments by mutableStateOf(1)
    var visible by mutableStateOf(numberOfFragments > 1)

    fun addElement() {
        numberOfFragments++
    }

    fun deleteElement() {
        numberOfFragments--
    }

    fun createNotification() {

    }
}