package com.fakhrirasyids.quickie.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.fakhrirasyids.quickie.platform.ui.QuickieApp
import com.fakhrirasyids.quickie.ui.theme.QuickieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuickieTheme {
                QuickieApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}