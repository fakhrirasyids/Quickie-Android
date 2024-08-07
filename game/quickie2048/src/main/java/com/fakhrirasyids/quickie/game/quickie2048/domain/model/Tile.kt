package com.fakhrirasyids.quickie.game.quickie2048.domain.model

data class Tile(
    val id: String,
    val value: Int,
    val currentPosition: Position,
    val previousPosition: Position? = null,
    val mergedFrom: Pair<Tile, Tile>? = null
) {
    val x: Int get() = currentPosition.x
    val y: Int get() = currentPosition.y
}