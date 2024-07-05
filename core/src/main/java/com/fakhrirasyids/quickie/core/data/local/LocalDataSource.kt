package com.fakhrirasyids.quickie.core.data.local

import kotlinx.coroutines.flow.Flow


interface LocalDataSource {

    // Language
    suspend fun setLanguagePreferences(language: String)
    fun getLanguagePreferences(): Flow<String>
}