package com.fakhrirasyids.quickie.platform.ui.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun HomeScreen() {
    Text(
        text = "Hello ${stringResource(id = com.fakhrirasyids.quickie.baseResource.R.string.app_name)}!",
        modifier = Modifier
    )
}