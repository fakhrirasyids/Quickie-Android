package com.fakhrirasyids.quickie.game.quickie2048.ui.components.board

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import com.fakhrirasyids.quickie.game.quickie2048.utils.Layer
import com.fakhrirasyids.quickie.game.quickie2048.utils.Layer.Companion.toZIndex
import kotlin.math.max
import kotlin.math.roundToInt

class Board(
    private val maxRows: Int,
    private val maxCols: Int,
) {
    @Stable
    fun Modifier.boardCell(row: Float, col: Float, layer: Layer): Modifier =
        layout { measurable, constraints ->
            val placeable = measurable.measure(constraints)
            layout(placeable.width, placeable.height) {
                placeable.place(
                    x = (placeable.width * col).roundToInt(),
                    y = (placeable.height * row).roundToInt(),
                    zIndex = layer.toZIndex()
                )
            }
        }

    val tileFraction: Float get() = 1f / max(maxRows, maxCols)
}
