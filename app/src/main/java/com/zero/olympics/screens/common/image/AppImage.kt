package com.zero.olympics.screens.common.image

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun AppImage(@DrawableRes drawableId: Int, modifier: Modifier = Modifier) {
    Image(painter = painterResource(drawableId), contentDescription = "", modifier = modifier)
}