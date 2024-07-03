package com.fakhrirasyids.quickie.core.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

class QuickiePreferences private constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        fun get(dataStore: DataStore<Preferences>) = QuickiePreferences(dataStore)
    }
}