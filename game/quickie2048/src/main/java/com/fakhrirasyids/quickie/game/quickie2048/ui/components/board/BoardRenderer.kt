package com.fakhrirasyids.quickie.game.quickie2048.ui.components.board

import androidx.compose.runtime.Composable
import com.fakhrirasyids.quickie.game.quickie2048.ui.components.tile.TileRenderer
import com.fakhrirasyids.quickie.game.quickie2048.ui.components.tile.TileRendererInstance
import com.fakhrirasyids.quickie.game.quickie2048.ui.model.BoardModel

interface BoardRenderer {
    @Composable
    fun Board.Render(boardModel: BoardModel)
}

internal object BoardRendererInstance : BoardRenderer, TileRenderer by TileRendererInstance {

    @Composable
    override fun Board.Render(boardModel: BoardModel) {
        boardModel.staticTiles.forEach { tileModel -> RenderStaticTile(tileModel) }
        boardModel.swipedTiles.forEach { tileModel -> RenderSwipedTile(tileModel) }
        boardModel.mergedTiles.forEach { tileModel -> RenderMergedTile(tileModel) }
        boardModel.newTiles.forEach { tileModel -> RenderNewTile(tileModel) }
    }
}
