package com.fakhrirasyids.quickie.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface MainRepository {

    // Language
    suspend fun setLanguagePreferences(language: String)
    fun getLanguagePreferences(): Flow<String>
    fun getLanguageLocale(): Flow<String>
}