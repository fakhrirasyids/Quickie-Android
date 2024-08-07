package com.freelanxx.quickie.game.quickie2048.domain.usecase

import com.freelanxx.quickie.game.quickie2048.domain.model.Direction
import com.freelanxx.quickie.game.quickie2048.domain.model.Grid

interface GameUseCase {
    fun getGrid(): Grid
    fun restart()
    fun move(direction: Direction)
    fun getScore(): Int
    fun getWon(): Boolean
    fun getOver(): Boolean
}