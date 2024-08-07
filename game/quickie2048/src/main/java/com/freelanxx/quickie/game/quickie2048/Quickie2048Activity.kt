package com.freelanxx.quickie.game.quickie2048

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fakhrirasyids.quickie.ui.theme.QuickieTheme
import com.freelanxx.quickie.game.quickie2048.di.repositoryModules
import com.freelanxx.quickie.game.quickie2048.di.useCaseModules
import com.freelanxx.quickie.game.quickie2048.di.viewModelModules
import com.freelanxx.quickie.game.quickie2048.ui.screen.GameScreen
import org.koin.core.context.loadKoinModules

class Quickie2048Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        loadKoinModules(
            listOf(
                repositoryModules,
                useCaseModules,
                viewModelModules
            )
        )

        setContent {
            QuickieTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    Box(
                        Modifier.padding(16.dp)
                    ) {
                        GameScreen()
                    }
                }
            }
        }
    }
}