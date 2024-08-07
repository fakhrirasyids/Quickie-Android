package com.fakhrirasyids.quickie.game.quickie2048.data

import com.fakhrirasyids.quickie.game.quickie2048.domain.model.Direction
import com.fakhrirasyids.quickie.game.quickie2048.domain.model.Direction.Companion.toVector
import com.fakhrirasyids.quickie.game.quickie2048.domain.model.FarthestPosition
import com.fakhrirasyids.quickie.game.quickie2048.domain.model.GameData
import com.fakhrirasyids.quickie.game.quickie2048.domain.model.Grid
import com.fakhrirasyids.quickie.game.quickie2048.domain.model.Position
import com.fakhrirasyids.quickie.game.quickie2048.domain.model.Tile
import com.fakhrirasyids.quickie.game.quickie2048.domain.model.Traversals
import com.fakhrirasyids.quickie.game.quickie2048.domain.model.Vector
import com.fakhrirasyids.quickie.game.quickie2048.domain.repository.GameRepository
import java.util.UUID
import kotlin.random.Random

class GameRepositoryImpl(
    private val size: Int = 4,
    private val startTiles: Int = 2
) : GameRepository {

    private var grid = Grid(size)
    private var gameData = GameData()

    init {
        grid.addStartTiles(startTiles)
    }

    override fun getGrid(): Grid = grid

    override fun restart() {
        grid = Grid(size)
        gameData = GameData()
        grid.addStartTiles(startTiles)
    }

    override fun move(direction: Direction) {
        if (isGameTerminated()) {
            return
        }

        val vector = direction.toVector()
        val traversals = buildTraversals(vector)
        var moved = false

        prepareTiles()

        traversals.x.forEach { x ->
            traversals.y.forEach { y ->
                val position = Position(x = x, y = y)
                grid.cellContent(position)?.let { tile ->
                    val positions = findFarthestPosition(position, vector)
                    val next = grid.cellContent(positions.next)
                    val newPosition =
                        if (next != null && next.value == tile.value && next.mergedFrom == null) {
                            val merged = Tile(
                                id = UUID.randomUUID().toString(),
                                currentPosition = positions.next,
                                value = tile.value * 2,
                                mergedFrom = tile to next
                            )
                            grid.insertTile(merged)
                            grid.removeTile(tile)
                            gameData.score += merged.value

                            if (merged.value == 2048) {
                                gameData.won = true
                            }
                            merged.currentPosition
                        } else {
                            moveTile(tile, positions.farthest)
                            positions.farthest
                        }

                    if (position != newPosition) {
                        moved = true
                    }
                }
            }
        }
        if (moved) {
            grid.addRandomTile()
            if (!movesAvailable()) {
                gameData.over = true
            }
        }
    }

    private fun movesAvailable(): Boolean =
        grid.areCellsAvailable() || tileMatchesAvailable()

    private fun tileMatchesAvailable(): Boolean {
        for (x in 0 until size) {
            for (y in 0 until size) {
                val position = Position(x, y)
                val tile = grid.cellContent(position)
                if (tile != null) {
                    for (direction in Direction.entries) {
                        val vector = direction.toVector()
                        val otherPosition = Position(x + vector.x, y + vector.y)
                        val otherTile = grid.cellContent(otherPosition)
                        if (otherTile != null && otherTile.value == tile.value) {
                            return true
                        }
                    }
                }
            }
        }
        return false
    }

    private fun prepareTiles() {
        grid.cells
            .flatten()
            .filterNotNull()
            .forEach {
                grid.cells[it.x][it.y] = it.copy(
                    previousPosition = it.currentPosition,
                    mergedFrom = null,
                )
            }
    }

    private fun moveTile(tile: Tile, position: Position) {
        grid.cells[tile.x][tile.y] = null
        grid.cells[position.x][position.y] = tile.copy(currentPosition = position)
    }

    private fun findFarthestPosition(position: Position, vector: Vector): FarthestPosition {
        var curPosition: Position = position
        var prevPosition: Position
        do {
            prevPosition = curPosition
            curPosition = Position(x = prevPosition.x + vector.x, y = prevPosition.y + vector.y)
        } while (grid.withinBounds(curPosition) && grid.isCellAvailable(
                curPosition
            )
        )
        return FarthestPosition(
            farthest = prevPosition,
            next = curPosition,
        )
    }

    private fun buildTraversals(vector: Vector) =
        Traversals(
            x = if (vector.x == 1) (size - 1 downTo 0) else (0 until size),
            y = if (vector.y == 1) (size - 1 downTo 0) else (0 until size),
        )

    private fun Grid.addStartTiles(startTiles: Int) {
        repeat(startTiles) {
            addRandomTile()
        }
    }

    private fun isGameTerminated() = gameData.over

    private fun Grid.addRandomTile() {
        if (availableCells().isNotEmpty()) {
            val value = if (Random.nextFloat() < 0.9f) 2 else 4
            randomAvailableCell()?.let {
                insertTile(
                    Tile(
                        id = UUID.randomUUID().toString(),
                        currentPosition = it,
                        value = value
                    )
                )
            }
        }
    }

    override fun getScore(): Int = gameData.score

    override fun getWon(): Boolean = gameData.won

    override fun getOver(): Boolean = gameData.over
}
