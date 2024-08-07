package com.fakhrirasyids.quickie.game.quickie2048.ui.screen.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.fakhrirasyids.quickie.ui.theme.LightBLue
import com.fakhrirasyids.quickie.ui.theme.QuickieTheme
import com.fakhrirasyids.quickie.ui.theme.SkyBlue
import com.fakhrirasyids.quickie.game.quickie2048.ui.components.board.Board
import com.fakhrirasyids.quickie.game.quickie2048.utils.Layer
import com.fakhrirasyids.quickie.game.quickie2048.utils.Layer.Companion.toZIndex
import kotlin.math.min

@Composable
fun BoardWidget(
    won: Boolean,
    over: Boolean,
    onTryAgainClicked: () -> Unit,
    modifier: Modifier = Modifier,
    maxRows: Int = 4,
    maxCols: Int = 4,
    content: @Composable Board.() -> Unit
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(1f)
//                .background(Color(backgroundColor), RoundedCornerShape(cornerRadius.dp))
                .background(LightBLue, RoundedCornerShape(cornerRadius.dp))
                .padding(innerPadding.dp)
                .boardBackground(maxRows, maxCols),
            content = { Board(maxRows, maxCols).content() }
        )
        AnimatedVisibility(
            visible = won || over,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Popup(
                text = stringResource(id = won.toPopupMessage()),
                textColor = won.toPopupMessageColor(),
                backgroundColor = won.toPopupBackgroundColor(),
                onTryAgainClicked = onTryAgainClicked
            )
        }
    }
}

@Composable
fun Popup(
    text: String,
    textColor: Color,
    backgroundColor: Color,
    onTryAgainClicked: () -> Unit,
    modifier: Modifier = Modifier,
    layer: Layer = Layer.PopupLayer,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .zIndex(layer.toZIndex())
            .background(backgroundColor, RoundedCornerShape(cornerRadius.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = text,
            fontSize = popupMessageFontSize.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
        )
        Button(
            onClick = onTryAgainClicked
        ) {
            Text(stringResource(id = com.fakhrirasyids.quickie.baseResource.R.string.popup_try_again))
        }
    }
}

@Composable
private fun Boolean.toPopupMessage() =
    (if (this) com.fakhrirasyids.quickie.baseResource.R.string.popup_message_win else com.fakhrirasyids.quickie.baseResource.R.string.popup_message_game_over)

private fun Boolean.toPopupMessageColor() =
    Color(if (this) popupMessageWinColor else popupMessageOverColor)

private fun Boolean.toPopupBackgroundColor() =
    Color(if (this) popupBackgroundWinColor else popupBackgroundOverColor)

@Preview
@Composable
private fun BoardEmptyPreview() {
    QuickieTheme {
        BoardWidget(
            won = false,
            over = false,
            onTryAgainClicked = { },
            content = {}
        )
    }
}

@Preview
@Composable
private fun BoardWonPreview() {
    QuickieTheme {
        BoardWidget(
            won = true,
            over = false,
            onTryAgainClicked = { },
            content = {}
        )
    }
}

@Preview
@Composable
private fun BoardGameOverPreview() {
    QuickieTheme {
        BoardWidget(
            won = false,
            over = true,
            onTryAgainClicked = { },
            content = {}
        )
    }
}

internal fun Modifier.boardBackground(
    maxRows: Int,
    maxCols: Int,
) = drawBehind {
    val fixFactorInPx = fixFactor.dp.toPx()
    val cellDim = min(size.width / maxCols, size.height / maxRows)
    for (row in 0 until maxRows) {
        for (col in 0 until maxCols) {
            val cellOffset =
                Offset(
                    x = col * cellDim + fixFactorInPx,
                    y = row * cellDim + fixFactorInPx
                )
            val cellSize = Size(
                width = cellDim - 2f * fixFactorInPx,
                height = cellDim - 2f * fixFactorInPx,
            )
            drawRoundRect(
                color = SkyBlue,
                topLeft = cellOffset,
                size = cellSize,
                cornerRadius = CornerRadius(cornerRadius.dp.toPx())
            )
        }
    }
}

private const val fixFactor = 5
private const val bgTileColor = 0xffcec1b2
private const val innerPadding = 4
private const val cornerRadius = 4
private const val backgroundColor = 0xffbfab9dL
private const val popupMessageFontSize = 56
private const val popupMessageWinColor = 0xffffffffL
private const val popupMessageOverColor = 0xff776e65L
private const val popupBackgroundWinColor = 0xa0edc301L
private const val popupBackgroundOverColor = 0x80ffffffL
