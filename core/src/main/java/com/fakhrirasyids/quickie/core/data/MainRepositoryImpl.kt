package com.fakhrirasyids.quickie.core.data

import com.fakhrirasyids.quickie.core.data.local.LocalDataSource
import com.fakhrirasyids.quickie.core.domain.repository.MainRepository

class MainRepositoryImpl(private val localDataSource: LocalDataSource) : MainRepository {
    val a = "a"
}