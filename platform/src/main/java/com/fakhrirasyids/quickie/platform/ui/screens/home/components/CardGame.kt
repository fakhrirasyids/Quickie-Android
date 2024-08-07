package com.fakhrirasyids.quickie.platform.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardGame(
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
    ) {
        Box(modifier = modifier.padding(8.dp)) {
            
        }
    }
}

@Preview
@Composable
fun CardGamePreview() {
    CardGame(modifier = Modifier)
}