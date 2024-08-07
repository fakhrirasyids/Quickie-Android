package com.fakhrirasyids.quickie.platform.ui.screens.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit
) {
    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnimation = animateFloatAsState(
        targetValue = if (startAnimation) 1F else 0F,
        animationSpec = tween(durationMillis = 300), label = "SplashScreenAlphaAnimation"
    )

    LaunchedEffect(key1 = true) {
        delay(300)
        startAnimation = true
        delay(1700)
        startAnimation = false
        delay(500)
        navigateToHome()
    }

    Splash(modifier = modifier, alpha = alphaAnimation.value)
}

@Composable
fun Splash(
    modifier: Modifier,
    alpha: Float
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = modifier
                .size(120.dp)
                .alpha(alpha),
            painter = painterResource(id = com.fakhrirasyids.quickie.baseResource.R.drawable.ic_quickie),
            tint = Color.Unspecified,
            contentDescription = stringResource(id = com.fakhrirasyids.quickie.baseResource.R.string.app_name)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SplashScreenPreview() {
    Splash(modifier = Modifier, alpha = 1f)
}