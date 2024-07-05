package com.fakhrirasyids.quickie.platform.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fakhrirasyids.quickie.platform.ui.components.Marquee
import com.fakhrirasyids.quickie.platform.ui.screens.home.components.SettingsDialog
import com.fakhrirasyids.quickie.ui.theme.Blue
import com.fakhrirasyids.quickie.ui.theme.LightBLue
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = koinViewModel()
) {
    val openSettingsDialog = remember {
        mutableStateOf(false)
    }

    Box(modifier = modifier.fillMaxSize()) {
        homeViewModel.refreshHomeContent.collectAsState().value.let { state ->
            if (state) {
                homeViewModel.refreshHomeContent()
            } else {
                HomeContent(
                    modifier = modifier,
                    homeViewModel = homeViewModel,
                    openSettingsDialog = openSettingsDialog
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier,
    homeViewModel: HomeViewModel,
    openSettingsDialog: MutableState<Boolean>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        TopHomeBar(modifier = modifier, openSettingsDialog = openSettingsDialog)

        if (openSettingsDialog.value) {
            SettingsDialog(
                homeViewModel = homeViewModel,
                onDismissRequest = {
                    openSettingsDialog.value = false
                },
            )
        }
    }
}

@Composable
fun TopHomeBar(
    modifier: Modifier,
    openSettingsDialog: MutableState<Boolean>
) {
    Box(modifier = modifier) {
        Column {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = modifier
                        .padding(start = 82.dp, bottom = 4.dp)
                        .align(Alignment.Bottom),
                    text = stringResource(com.fakhrirasyids.quickie.baseResource.R.string.app_name),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp
                )

                Icon(
                    modifier = modifier
                        .clickable { openSettingsDialog.value = !openSettingsDialog.value }
                        .padding(12.dp),
                    imageVector = Icons.Default.Settings,
                    contentDescription = stringResource(id = com.fakhrirasyids.quickie.baseResource.R.string.settings_title)
                )
            }
            HorizontalDivider(color = Color.Black, thickness = 2.dp)
            Marquee(
                modifier = modifier
                    .padding(start = 72.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                LightBLue,
                                Blue
                            )
                        )
                    )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Separator",
                        tint = Color.Yellow
                    )
                    Text(
                        text = stringResource(id = com.fakhrirasyids.quickie.baseResource.R.string.home_marquee_slides_one),
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Separator",
                        tint = Color.Yellow
                    )
                    Text(
                        text = stringResource(id = com.fakhrirasyids.quickie.baseResource.R.string.home_marquee_slides_two),
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Separator",
                        tint = Color.Yellow
                    )
                    Text(
                        text = stringResource(id = com.fakhrirasyids.quickie.baseResource.R.string.home_marquee_slides_three),
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Separator",
                        tint = Color.Yellow
                    )
                    Text(
                        text = stringResource(id = com.fakhrirasyids.quickie.baseResource.R.string.home_marquee_slides_four),
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Separator",
                        tint = Color.Yellow
                    )
                    Text(
                        text = stringResource(id = com.fakhrirasyids.quickie.baseResource.R.string.home_marquee_slides_five),
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                }
            }
        }

        Card(
            onClick = {},
            modifier = modifier
                .wrapContentSize(unbounded = true)
                .padding(14.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            border = BorderStroke(2.dp, Color.Black)
        ) {
            Icon(
                modifier = modifier
                    .size(64.dp)
                    .padding(8.dp),
                painter = painterResource(id = com.fakhrirasyids.quickie.baseResource.R.drawable.quickie_logo_transparent),
                tint = Color.Unspecified,
                contentDescription = stringResource(id = com.fakhrirasyids.quickie.baseResource.R.string.app_name)
            )
        }
    }
}