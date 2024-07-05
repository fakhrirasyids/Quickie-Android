package com.fakhrirasyids.quickie.core.domain.usecase

import kotlinx.coroutines.flow.Flow

interface MainUseCase {

    // Language
    suspend fun setLanguagePreferences(language: String)
    fun getLanguagePreferences(): Flow<String>
    fun getLanguageLocale(): Flow<String>
}