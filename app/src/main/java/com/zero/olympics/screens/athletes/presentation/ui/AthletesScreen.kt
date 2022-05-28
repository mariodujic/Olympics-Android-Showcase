package com.zero.olympics.screens.athletes.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.zero.olympics.R
import com.zero.olympics.data.network.result.Result
import com.zero.olympics.screens.athletes.presentation.viewmodel.AthletesViewModel
import com.zero.olympics.screens.common.ui.mediumPadding

@Composable
fun AthletesScreen() {

    val viewModel: AthletesViewModel = hiltViewModel()
    val gamesResult by viewModel.games.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.athletes_top_bar_title))
        })
    }) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val result = gamesResult) {
                Result.Empty -> Text(text = stringResource(id = R.string.athletes_screen_empty))
                is Result.Error -> Text(text = stringResource(id = R.string.athletes_screen_error))
                Result.Loading -> CircularProgressIndicator()
                is Result.Success -> AthletesSuccessScreen(gameAthletes = result.value)
            }
        }
    }
}