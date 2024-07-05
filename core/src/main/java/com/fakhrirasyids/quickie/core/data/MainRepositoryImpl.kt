package com.fakhrirasyids.quickie.core.data

import com.fakhrirasyids.quickie.core.data.local.LocalDataSource
import com.fakhrirasyids.quickie.core.domain.repository.MainRepository
import com.fakhrirasyids.quickie.core.utils.LanguageUtils
import kotlinx.coroutines.flow.flow

class MainRepositoryImpl(private val localDataSource: LocalDataSource) : MainRepository {

    // Language
    override suspend fun setLanguagePreferences(language: String) {
        localDataSource.setLanguagePreferences(language)
    }

    override fun getLanguagePreferences() = localDataSource.getLanguagePreferences()

    override fun getLanguageLocale() = flow {
        getLanguagePreferences().collect { lang ->
            when (lang) {
                LanguageUtils.Language.INDONESIA.toString() -> emit(LanguageUtils.LanguageCode.ID.name)
                LanguageUtils.Language.JAPANESE.toString() -> emit(LanguageUtils.LanguageCode.JA.name)
                LanguageUtils.Language.CHINESE.toString() -> emit(LanguageUtils.LanguageCode.ZH.name)
                LanguageUtils.Language.ARABIC.toString() -> emit(LanguageUtils.LanguageCode.AR.name)
                else -> emit(LanguageUtils.LanguageCode.EN.name)
            }
        }
    }
}