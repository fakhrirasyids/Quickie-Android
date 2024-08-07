package com.fakhrirasyids.quickie.game.quickie2048.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhrirasyids.quickie.game.quickie2048.domain.model.Direction
import com.fakhrirasyids.quickie.game.quickie2048.domain.model.Grid
import com.fakhrirasyids.quickie.game.quickie2048.domain.usecase.GameUseCase
import com.fakhrirasyids.quickie.game.quickie2048.ui.model.BoardModel
import com.fakhrirasyids.quickie.game.quickie2048.ui.model.TileModel
import com.fakhrirasyids.quickie.game.quickie2048.ui.model.TilePosition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID
import kotlin.math.abs

class GameScreenViewModel(
    private val gameUseCase: GameUseCase
) : ViewModel() {
    var boardModel by mutableStateOf(gameUseCase.toBoardModel())
        private set

    var score by mutableStateOf(0)
        private set

//    var bestScore by mutableStateOf(gameUseCase.getBestScore())
//        private set

    var won by mutableStateOf(gameUseCase.getWon())
        private set

    var over by mutableStateOf(gameUseCase.getOver())
        private set

    fun restartGame() {
        viewModelScope.launch(Dispatchers.Default) {
            gameUseCase.restart()
            score = 0
            boardModel = gameUseCase.toBoardModel()
            won = gameUseCase.getWon()
            over = gameUseCase.getOver()
        }
    }

    fun applyDragGesture(dragOffset: Offset) {
        viewModelScope.launch(Dispatchers.Default) {
            val direction = if (abs(dragOffset.x) > abs(dragOffset.y)) {
                if (dragOffset.x > 0) {
                    Direction.Right
                } else {
                    Direction.Left
                }
            } else {
                if (dragOffset.y > 0) {
                    Direction.Down
                } else {
                    Direction.Up
                }
            }
            gameUseCase.move(direction)
            boardModel = gameUseCase.toBoardModel()
            score = gameUseCase.getScore()
//            bestScore = gameUseCase.getBestScore()
            won = gameUseCase.getWon()
            over = gameUseCase.getOver()
        }
    }

    private companion object {
        fun GameUseCase.toBoardModel() =
            BoardModel(
                newTiles = getGrid().toNewTiles(),
                staticTiles = getGrid().toStaticTiles(),
                swipedTiles = getGrid().toSwipedTiles(),
                mergedTiles = getGrid().toMergedTiles(),
            )

        fun Grid.toNewTiles() = cells
            .flatten()
            .filterNotNull()
            .filter { it.mergedFrom == null && it.previousPosition == null }
            .map {
                TileModel(
                    id = it.id,
                    curPosition = TilePosition(
                        row = it.y.toFloat(),
                        col = it.x.toFloat(),
                    ),
                    curValue = it.value,
                )
            }

        fun Grid.toStaticTiles() = cells
            .flatten()
            .filterNotNull()
            .filter { it.mergedFrom == null && it.previousPosition != null && it.currentPosition == it.previousPosition }
            .map {
                TileModel(
                    id = it.id,
                    curPosition = TilePosition(
                        row = it.y.toFloat(),
                        col = it.x.toFloat(),
                    ),
                    curValue = it.value,
                )
            }

        fun Grid.toSwipedTiles() = cells
            .flatten()
            .filterNotNull()
            .flatMap {
                if (it.previousPosition != null && it.currentPosition != it.previousPosition) {
                    listOf(
                        TileModel(
                            id = it.id,
                            curPosition = TilePosition(
                                row = it.y.toFloat(),
                                col = it.x.toFloat(),
                            ),
                            prevPosition = TilePosition(
                                row = it.previousPosition.y.toFloat(),
                                col = it.previousPosition.x.toFloat(),
                            ),
                            curValue = it.value,
                        )
                    )
                } else if (it.mergedFrom != null) {
                    val prevValue = it.mergedFrom.first.value
                    listOf(
                        TileModel(
                            id = UUID.randomUUID().toString(),
                            curPosition = TilePosition(
                                row = it.y.toFloat(),
                                col = it.x.toFloat(),
                            ),
                            prevPosition = TilePosition(
                                row = it.mergedFrom.first.currentPosition.y.toFloat(),
                                col = it.mergedFrom.first.currentPosition.x.toFloat(),
                            ),
                            curValue = prevValue,
                        ),
                        TileModel(
                            id = UUID.randomUUID().toString(),
                            curPosition = TilePosition(
                                row = it.y.toFloat(),
                                col = it.x.toFloat(),
                            ),
                            prevPosition = TilePosition(
                                row = it.mergedFrom.second.previousPosition!!.y.toFloat(),
                                col = it.mergedFrom.second.previousPosition!!.x.toFloat(),
                            ),
                            curValue = prevValue,
                        ),
                    )
                } else emptyList()
            }

        fun Grid.toMergedTiles() = cells
            .flatten()
            .filterNotNull()
            .filter { it.mergedFrom != null }
            .map {
                TileModel(
                    id = it.id,
                    curPosition = TilePosition(
                        row = it.y.toFloat(),
                        col = it.x.toFloat(),
                    ),
                    curValue = it.value,
                )
            }
    }
}
