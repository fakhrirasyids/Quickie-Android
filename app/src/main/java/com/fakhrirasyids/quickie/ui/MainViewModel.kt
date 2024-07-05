package com.fakhrirasyids.quickie.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fakhrirasyids.quickie.core.domain.usecase.MainUseCase

class MainViewModel(private val mainUseCase: MainUseCase) : ViewModel() {
    fun getLanguageSettings() = mainUseCase.getLanguageLocale().asLiveData()
}