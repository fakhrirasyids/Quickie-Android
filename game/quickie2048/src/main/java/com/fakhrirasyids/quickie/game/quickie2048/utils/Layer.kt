package com.fakhrirasyids.quickie.game.quickie2048.utils

sealed class Layer {
    data object CellLayer : Layer()
    data object AnimationLayer : Layer()
    data object MergeLayer : Layer()
    data object PopupLayer : Layer()

    companion object {
        fun Layer.toZIndex() =
            when (this) {
                CellLayer -> 0.0f
                AnimationLayer -> 1.0f
                MergeLayer -> 2.0f
                PopupLayer -> 3.0f
            }
    }
}