package com.fakhrirasyids.quickie.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fakhrirasyids.quickie.core.data.local.room.dao.LeaderboardDao
import com.fakhrirasyids.quickie.core.domain.model.Leaderboard

@Database(entities = [Leaderboard::class], version = 1, exportSchema = false)
abstract class QuickieDatabase: RoomDatabase() {
    abstract fun leaderboardDao(): LeaderboardDao
}