package com.fakhrirasyids.quickie.core.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.fakhrirasyids.quickie.core.utils.LanguageUtils
import kotlinx.coroutines.flow.map

class QuickiePreferences private constructor(private val dataStore: DataStore<Preferences>) {

    fun getLanguagePreferences() =
        dataStore.data.map { it[LANGUAGE_PREFERENCES] ?: LanguageUtils.Language.CHINESE.toString() }

    suspend fun setLanguagePreferences(language: String) {
        dataStore.edit { prefs ->
            prefs[LANGUAGE_PREFERENCES] = language
        }
    }

    companion object {
        private val LANGUAGE_PREFERENCES = stringPreferencesKey("language_preferences")

        fun get(dataStore: DataStore<Preferences>) = QuickiePreferences(dataStore)
    }
}