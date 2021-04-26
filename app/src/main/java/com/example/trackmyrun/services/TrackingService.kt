package com.example.trackmyrun.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.example.trackmyrun.R
import com.example.trackmyrun.other.Constants.ACTION_PAUSE_SERVICE
import com.example.trackmyrun.other.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import com.example.trackmyrun.other.Constants.ACTION_START_OR_RESUME_SERVICE
import com.example.trackmyrun.other.Constants.ACTION_STOP_SERVICE
import com.example.trackmyrun.other.Constants.NOTIFICATION_CHANNEL_ID
import com.example.trackmyrun.other.Constants.NOTIFICATION_CHANNEL_NAME
import com.example.trackmyrun.other.Constants.NOTIFICATION_ID
import com.example.trackmyrun.ui.MainActivity
import timber.log.Timber

class TrackingService : LifecycleService(){

    var isFirstRun = true
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let { intent ->
            when(intent.action){
                ACTION_START_OR_RESUME_SERVICE -> {
                    if(isFirstRun){
                        startForegroundService()
                        isFirstRun = false
                    }else{
                        Timber.d("Resuming Service")
                    }
                }
                ACTION_PAUSE_SERVICE -> Timber.d("Paused service")
                ACTION_STOP_SERVICE -> Timber.d("Stopped service")
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

//Use notification manager to create notification channel for devices on O and above
    private fun startForegroundService(){
        //Android Framework service to create notification
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) createNotificationChannel(notificationManager)

        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false) //set notifcation to always active
            .setOngoing(true) //Notification can't be swipped away
            .setSmallIcon(R.drawable.ic_directions_run_black_24dp)
            .setContentTitle("Running App")
            .setContentText("00:00:00")
            .setContentIntent(getMainActivityPendingIntent())//set pending intent to return to Main Activity which will launch Track Fragment on notification click

        startForeground(NOTIFICATION_ID, notificationBuilder.build())

    }

    private fun getMainActivityPendingIntent() = PendingIntent.getActivity(
        this,
        0,
        Intent(this, MainActivity::class.java).also { intent ->
            intent.action = ACTION_SHOW_TRACKING_FRAGMENT
        },
        FLAG_UPDATE_CURRENT //When we launch pending intent and it already exists, update instead of recreating the intent
    )
    //For Android Oreo and above create notification channel
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager){
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            IMPORTANCE_LOW
        )

        notificationManager.createNotificationChannel(channel)
    }

}