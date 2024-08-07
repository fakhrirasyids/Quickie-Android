package com.freelanxx.quickie.game.quickie2048.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

object Constants {
    @Composable
    fun Int.resolve() = LocalContext.current.getString(this)

    fun <T> List<T>.toArrayList(): ArrayList<T> = ArrayList(this)

    const val TILE_PADDING = 4
    const val TILE_CORNER_RADIUS = 4

    const val NEW_DURATION = 100
    const val SWIPE_DURATION = 100
    const val MERGE_DURATION = 200
}