package com.example.foregroundactivity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat

class ForegroundService : Service() {

    private lateinit var mediaPlayer: MediaPlayer
    private var CHANNEL_ID = "ForegroundServiceChannel"
    private lateinit var notifManager: NotificationManager;

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "Service created", Toast.LENGTH_SHORT).show()
        mediaPlayer = MediaPlayer.create(this, R.raw.song)
        mediaPlayer.isLooping = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val offerChannelName = "Service Channel"
            val offerChannelDescription = "Music Channel"
            val offerChannelImportance = NotificationManager.IMPORTANCE_DEFAULT
            val notifChannel = NotificationChannel(CHANNEL_ID, offerChannelName, offerChannelImportance)
            notifChannel.description = offerChannelDescription
            notifManager = getSystemService(NotificationManager::class.java)
            notifManager.createNotificationChannel(notifChannel)
        }
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Music Service")
            .setContentText("Music is playing in the background")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
        startForeground(1, notification)
        return START_STICKY;
    }


    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Service destroyed", Toast.LENGTH_SHORT).show()
        mediaPlayer.stop()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null;
    }
}