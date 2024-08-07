package com.fakhrirasyids.quickie.game.quickie2048.ui.model

import androidx.compose.runtime.Immutable

@Immutable
data class TileModel(
    val id: String,
    val curValue: Int,
    val curPosition: TilePosition,
    val prevPosition: TilePosition? = null,
)