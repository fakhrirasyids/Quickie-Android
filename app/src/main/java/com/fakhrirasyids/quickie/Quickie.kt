package com.fakhrirasyids.quickie

import android.app.Application
import com.fakhrirasyids.quickie.core.di.dataSourceModules
import com.fakhrirasyids.quickie.core.di.dataStoreModules
import com.fakhrirasyids.quickie.core.di.databaseModules
import com.fakhrirasyids.quickie.core.di.repositoryModules
import com.fakhrirasyids.quickie.core.di.useCaseModules
import com.google.android.play.core.splitcompat.SplitCompatApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Quickie : SplitCompatApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Quickie)
            modules(
                dataStoreModules,
                databaseModules,
                dataSourceModules,
                repositoryModules,
                useCaseModules
            )
        }
    }
}