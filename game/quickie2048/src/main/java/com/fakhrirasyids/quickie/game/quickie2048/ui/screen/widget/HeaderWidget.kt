package com.fakhrirasyids.quickie.game.quickie2048.ui.screen.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fakhrirasyids.quickie.baseResource.R
import com.fakhrirasyids.quickie.ui.theme.QuickieTheme

@Composable
fun HeaderWidget(
    score: Int,
//    bestScore: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        HeaderTitle()
        HeaderPanel(title = stringResource(R.string.header_score), value = score.toString())
        Spacer(modifier = Modifier.requiredWidth(headerSpacing.dp))
//        HeaderPanel(title = stringResource(com.fakhrirasyids.quickie.baseResource.R.string.header_best), value = bestScore.toString())
    }
}

@Composable
fun RowScope.HeaderTitle(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.weight(1f),
        text = stringResource(id = com.fakhrirasyids.quickie.baseResource.R.string.app_name_short),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = Color(titleColor)
    )
}

@Composable
fun HeaderPanel(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .wrapContentWidth(unbounded = true)
            .requiredHeight(panelHeight.dp)
            .background(Color(panelColor), RoundedCornerShape(panelCornerRadius.dp))
            .padding(horizontal = panelPadding.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.bodyMedium,
            color = Color(panelTitleColor),
            modifier = Modifier.padding(bottom = panelTitlePaddingBottom.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = Color(panelValueColor)
        )
    }
}

@Preview
@Composable
private fun HeaderPreview() {
    QuickieTheme {
        HeaderWidget(score = 128,
//            bestScore = 65536
        )
    }
}

private const val headerSpacing = 4
private const val panelHeight = 48
private const val panelPadding = 8
private const val panelCornerRadius = 2
private const val panelColor = 0xffbfab9d
private const val panelTitlePaddingBottom = 28
private const val panelTitleColor = 0xfff0e4d9
private const val panelValueColor = 0xffffffff
private const val titleColor = 0xff776e65
