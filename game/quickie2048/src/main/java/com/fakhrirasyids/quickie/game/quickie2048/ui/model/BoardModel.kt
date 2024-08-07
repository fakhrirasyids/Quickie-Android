package com.fakhrirasyids.quickie.game.quickie2048.ui.model

import androidx.compose.runtime.Stable

@Stable
data class BoardModel(
    val newTiles: List<TileModel> = emptyList(),
    val staticTiles: List<TileModel> = emptyList(),
    val swipedTiles: List<TileModel> = emptyList(),
    val mergedTiles: List<TileModel> = emptyList(),
)