package com.fakhrirasyids.quickie.game.quickie2048.domain.interactor

import com.fakhrirasyids.quickie.game.quickie2048.domain.model.Direction
import com.fakhrirasyids.quickie.game.quickie2048.domain.repository.GameRepository
import com.fakhrirasyids.quickie.game.quickie2048.domain.usecase.GameUseCase

class GameInteractor(private val gameRepository: GameRepository) : GameUseCase {
    override fun getGrid() = gameRepository.getGrid()

    override fun restart() {
        gameRepository.restart()
    }

    override fun move(direction: Direction) {
        gameRepository.move(direction)
    }

    override fun getScore() = gameRepository.getScore()

    override fun getWon() = gameRepository.getWon()

    override fun getOver() = gameRepository.getOver()
}