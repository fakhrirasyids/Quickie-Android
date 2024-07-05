package com.fakhrirasyids.quickie.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.fakhrirasyids.quickie.common.ContextUtils
import com.fakhrirasyids.quickie.core.utils.LanguageUtils
import com.fakhrirasyids.quickie.di.viewModelModules
import com.fakhrirasyids.quickie.platform.ui.QuickieApp
import com.fakhrirasyids.quickie.ui.theme.QuickieTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import java.util.Locale

class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(viewModelModules)
        enableEdgeToEdge()
        setContent {
            QuickieTheme {
                QuickieApp(modifier = Modifier.fillMaxSize())
            }
        }

        mainViewModel.getLanguageSettings().observe(this) { lang ->
            updateLocale(Locale(lang))
        }
    }

    private fun updateLocale(locale: Locale) {
        val context = ContextUtils.updateLocale(this, locale)
        resources.updateConfiguration(
            context.resources.configuration,
            context.resources.displayMetrics
        )
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(
            ContextUtils.updateLocale(
                newBase,
                Locale(LanguageUtils.LanguageCode.EN.name)
            )
        )
    }
}