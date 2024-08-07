package com.freelanxx.quickie.game.quickie2048.domain.model

enum class Direction {
    Left, Right, Up, Down;

    companion object {
        internal fun Direction.toVector() =
            when (this) {
                Left -> Vector(x = -1, y = 0)
                Right -> Vector(x = 1, y = 0)
                Up -> Vector(x = 0, y = -1)
                Down -> Vector(x = 0, y = 1)
            }
    }
}