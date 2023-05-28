package com.journey.bangkit

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.journey.bangkit.ui.theme.JourneyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var keepSplashOnScreen = true
        installSplashScreen().setKeepOnScreenCondition {
            keepSplashOnScreen
        }
        Handler(Looper.getMainLooper()).postDelayed({
            keepSplashOnScreen = false
        }, 1500L)

        setContent {
            JourneyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JourneyApp()
                }
            }
        }
    }
}