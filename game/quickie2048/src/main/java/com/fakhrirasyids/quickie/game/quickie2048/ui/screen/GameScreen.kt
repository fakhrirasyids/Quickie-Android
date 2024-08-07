package com.fakhrirasyids.quickie.game.quickie2048.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import com.fakhrirasyids.quickie.ui.theme.QuickieTheme
import com.fakhrirasyids.quickie.game.quickie2048.ui.components.board.BoardRendererInstance
import com.fakhrirasyids.quickie.game.quickie2048.ui.screen.widget.BoardWidget
import com.fakhrirasyids.quickie.game.quickie2048.ui.screen.widget.HeaderWidget
import com.fakhrirasyids.quickie.game.quickie2048.ui.screen.widget.SubHeaderWidget
import org.koin.androidx.compose.koinViewModel

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    size: Int = 4,
    viewModel: GameScreenViewModel = koinViewModel()
) {
    Scaffold { innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.align(Alignment.TopCenter)
            ) {
                HeaderWidget(
                    score = viewModel.score,
//                bestScore = viewModel.bestScore,
                )
                SubHeaderWidget(
                    onResetClicked = { viewModel.restartGame() }
                )
            }

            BoardWidget(
                maxRows = size,
                maxCols = size,
                onTryAgainClicked = { viewModel.restartGame() },
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize()
                    .dragDetector(
                        enabled = !viewModel.won && !viewModel.over,
                        dragOffset = rememberDragOffset(),
                        onDragFinished = { dragOffset -> viewModel.applyDragGesture(dragOffset) }
                    ),
                won = viewModel.won,
                over = viewModel.over,
            ) {
                BoardRendererInstance.apply {
                    Render(viewModel.boardModel)
                }
            }
        }
    }
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
internal fun Modifier.dragDetector(
    enabled: Boolean,
    dragOffset: MutableState<Offset>,
    onDragFinished: (Offset) -> Unit,
) = pointerInput(Unit) {
    if (enabled) {
        detectDragGestures(
            onDragStart = { dragOffset.value = Offset(0f, 0f) },
            onDragEnd = { onDragFinished(dragOffset.value) }
        ) { change, dragAmount ->
            change.consume()
            dragOffset.value += Offset(dragAmount.x, dragAmount.y)
        }
    }
}

@Composable
internal fun rememberDragOffset() = remember { mutableStateOf(Offset(0f, 0f)) }

@Preview
@Composable
private fun GamePreview() {
    QuickieTheme {
        GameScreen()
    }
}
