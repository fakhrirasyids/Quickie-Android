package com.fakhrirasyids.quickie.game.quickie2048.domain.repository

import com.fakhrirasyids.quickie.game.quickie2048.domain.model.Direction
import com.fakhrirasyids.quickie.game.quickie2048.domain.model.Grid

interface GameRepository {
    fun getGrid(): Grid
    fun restart()
    fun move(direction: Direction)
    fun getScore(): Int
    fun getWon(): Boolean
    fun getOver(): Boolean
}