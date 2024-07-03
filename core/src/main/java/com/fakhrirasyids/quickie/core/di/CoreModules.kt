package com.fakhrirasyids.quickie.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.fakhrirasyids.quickie.core.data.MainRepositoryImpl
import com.fakhrirasyids.quickie.core.data.local.LocalDataSource
import com.fakhrirasyids.quickie.core.data.local.LocalDataSourceImpl
import com.fakhrirasyids.quickie.core.data.local.datastore.QuickiePreferences
import com.fakhrirasyids.quickie.core.data.local.room.QuickieDatabase
import com.fakhrirasyids.quickie.core.data.local.room.dao.LeaderboardDao
import com.fakhrirasyids.quickie.core.domain.interactor.MainInteractor
import com.fakhrirasyids.quickie.core.domain.repository.MainRepository
import com.fakhrirasyids.quickie.core.domain.usecase.MainUseCase
import com.fakhrirasyids.quickie.core.utils.LocalStorageUtils
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settingsPreferences")

val dataStoreModules = module {
    single { QuickiePreferences.get(androidContext().dataStore) }
}

val databaseModules = module {
    factory { get<QuickieDatabase>().leaderboardDao() }
    single {
        val passphrase: ByteArray =
            SQLiteDatabase.getBytes(LocalStorageUtils.DATABASE_NAME.toCharArray())
        val factory = SupportFactory(passphrase)

        Room.databaseBuilder(
            androidContext(),
            QuickieDatabase::class.java, LocalStorageUtils.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val dataSourceModules = module {
    factory<LocalDataSource> { LocalDataSourceImpl(get<LeaderboardDao>(), get<QuickiePreferences>()) }
}

val repositoryModules = module {
    single<MainRepository> { MainRepositoryImpl(get<LocalDataSource>()) }
}

val useCaseModules = module {
    factory<MainUseCase> { MainInteractor(get<MainRepository>()) }
}