package com.fakhrirasyids.quickie.core.domain.interactor

import com.fakhrirasyids.quickie.core.domain.repository.MainRepository
import com.fakhrirasyids.quickie.core.domain.usecase.MainUseCase

class MainInteractor(private val repository: MainRepository) : MainUseCase {

    // Language
    override suspend fun setLanguagePreferences(language: String) {
        repository.setLanguagePreferences(language)
    }

    override fun getLanguagePreferences() = repository.getLanguagePreferences()
    override fun getLanguageLocale() = repository.getLanguageLocale()
}