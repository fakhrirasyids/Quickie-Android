package com.fakhrirasyids.quickie.core.utils

object LocalStorageUtils {
    const val DATABASE_NAME = "quickie_db"
}

object LanguageUtils {
    enum class Language(private val displayName: String) {
        ENGLISH("English"),
        INDONESIA("Indonesia"),
        JAPANESE("Japanese"),
        CHINESE("Chinese"),
        ARABIC("Arabic");

        override fun toString(): String = displayName

        companion object {
            fun getLanguagesList() = entries.map { it.toString() }.toTypedArray()
        }
    }

    enum class LanguageCode {
        EN,
        ID,
        JA,
        ZH,
        AR
    }
}