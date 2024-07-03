package com.fakhrirasyids.quickie.core.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("leaderboard")
data class Leaderboard(
    @PrimaryKey val name: String
)