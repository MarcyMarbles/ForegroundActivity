package com.example.foregroundactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.foregroundactivity.ui.theme.ForegroundActivityTheme

class MainActivity : ComponentActivity() {
    private lateinit var serviceIntent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        serviceIntent = Intent(this, ForegroundService::class.java)
        findViewById<Button>(R.id.startService).setOnClickListener {
            startService()
        }
        findViewById<Button>(R.id.stopService).setOnClickListener {
            stopService()
        }
        findViewById<Button>(R.id.nextActivity).setOnClickListener {
            nextActivity()
        }
    }

    private fun startService() {
        startService(serviceIntent)
    }

    private fun stopService() {
        stopService(serviceIntent)
    }

    private fun nextActivity() {
        startActivity(Intent(this, MainActivity2::class.java))
    }
}