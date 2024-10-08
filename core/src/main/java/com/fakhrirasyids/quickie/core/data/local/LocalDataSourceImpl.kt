package com.fakhrirasyids.quickie.core.data.local

import com.fakhrirasyids.quickie.core.data.local.datastore.QuickiePreferences
import com.fakhrirasyids.quickie.core.data.local.room.dao.LeaderboardDao

class LocalDataSourceImpl(
    private val leaderboardDao: LeaderboardDao,
    private val quickiePreferences: QuickiePreferences
) : LocalDataSource {

    // Language
    override suspend fun setLanguagePreferences(language: String) {
        quickiePreferences.setLanguagePreferences(language)
    }

    override fun getLanguagePreferences() = quickiePreferences.getLanguagePreferences()
}