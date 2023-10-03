package com.example.ajarin.android.landing.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ajarin.android.R
import com.example.ajarin.android.core_ui.theme.AjarinTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onFinish: () -> Unit
) {
    val currentOnFinish by rememberUpdatedState(onFinish)

    LaunchedEffect(true) {
        delay(2000)
        currentOnFinish()
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_ajarin),
            contentDescription = "Ajarin Logo",
            modifier = Modifier
                .size(200.dp)
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    AjarinTheme {
        SplashScreen {

        }
    }
}