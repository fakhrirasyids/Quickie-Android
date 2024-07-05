package com.fakhrirasyids.quickie.platform.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhrirasyids.quickie.core.domain.usecase.MainUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val mainUseCase: MainUseCase) : ViewModel() {

    private val _refreshHomeContent: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val refreshHomeContent: StateFlow<Boolean> = _refreshHomeContent

    private val _languagePreferences = MutableStateFlow("")
    val languagePreferences: StateFlow<String> = _languagePreferences

    init {
        updateLanguage()
        refreshHomeContent()
    }

    fun setLanguagePreferences(language: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mainUseCase.setLanguagePreferences(language)
                updateLanguage()
            } catch (e: Exception) {
                // Silent Exception
            }
        }
    }

    private fun updateLanguage() {
        viewModelScope.launch(Dispatchers.IO) {
            mainUseCase.getLanguagePreferences().collect { lang ->
                _languagePreferences.value = lang
                _refreshHomeContent.value = true
            }
        }
    }

    fun refreshHomeContent() {
        _refreshHomeContent.value = false
    }
}