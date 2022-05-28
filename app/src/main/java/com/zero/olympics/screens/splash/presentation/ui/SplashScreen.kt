package com.zero.olympics.screens.splash.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import com.zero.olympics.R
import com.zero.olympics.screens.common.image.AppImage
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigateHome: () -> Unit) {

    val splashTime = integerResource(id = R.integer.splash_screen_length_ms).toLong()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant)
    ) {
        AppImage(drawableId = R.drawable.ic_olympic_ring)
    }
    LaunchedEffect(Unit) {
        delay(splashTime)
        navigateHome()
    }
}