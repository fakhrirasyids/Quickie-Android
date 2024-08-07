package com.freelanxx.quickie.game.quickie2048.ui.components.tile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.freelanxx.quickie.game.quickie2048.domain.model.Tile
import com.freelanxx.quickie.game.quickie2048.utils.Constants

@Composable
fun Tile(
    fraction: Float,
    value: String,
    color: Color,
    fontSize: TextUnit,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(fraction)
            .padding(Constants.TILE_PADDING.dp)
            .background(
                backgroundColor, RoundedCornerShape(Constants.TILE_CORNER_RADIUS.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}