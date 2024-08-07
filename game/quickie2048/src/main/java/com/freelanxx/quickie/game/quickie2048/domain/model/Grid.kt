package com.freelanxx.quickie.game.quickie2048.domain.model

import com.freelanxx.quickie.game.quickie2048.utils.Constants.toArrayList
import kotlin.random.Random

data class Grid(
    val size: Int,
    val cells: ArrayList<ArrayList<Tile?>> =
        (0 until size).map {
            (0 until size).map {
                null
            }.toArrayList<Tile?>()
        }.toArrayList()
) {
    fun insertTile(tile: Tile) {
        cells[tile.x][tile.y] = tile
    }

    fun removeTile(tile: Tile) {
        cells[tile.x][tile.y] = null
    }

    fun randomAvailableCell(): Position? {
        val availableCells = availableCells()
        return if (availableCells.isNotEmpty()) {
            availableCells[Random.nextInt(availableCells.size)]
        } else null
    }

    fun isCellAvailable(position: Position) =
        !isCellOccupied(position)

    fun isCellOccupied(position: Position) =
        getCellContent(position) != null

    private fun getCellContent(position: Position): Tile? =
        if (withinBounds(position)) {
            cells[position.x][position.y]
        } else {
            null
        }

    fun withinBounds(position: Position) =
        position.x >= 0 && position.x < this.size &&
                position.y >= 0 && position.y < this.size

    fun availableCells(): List<Position> =
        (0 until size).flatMap { x ->
            (0 until size)
                .filter { y -> cells[x][y] == null }
                .map { y -> Position(x, y) }
        }

    fun cellContent(position: Position): Tile? =
        if (withinBounds(position)) {
            cells[position.x][position.y]
        } else {
            null
        }

    fun areCellsAvailable(): Boolean =
        availableCells().isNotEmpty()
}
