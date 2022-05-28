package com.zero.olympics.screens.athletes.details.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.zero.olympics.R
import com.zero.olympics.data.network.result.Result
import com.zero.olympics.screens.athletes.details.presentation.viewmodel.AthleteDetailsViewModel

@Composable
fun AthleteDetailsScreen(navigateBack: () -> Unit) {

    val viewModel: AthleteDetailsViewModel = hiltViewModel()
    val athleteResult by viewModel.athlete.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.athletes_top_bar_title)) },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.navigate_back_button_content_description)
                        )
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val result = athleteResult) {
                Result.Empty, is Result.Error -> Text(text = stringResource(id = R.string.athlete_screen_error))
                Result.Loading -> CircularProgressIndicator()
                is Result.Success -> AthleteDetailsSuccessScreen(result.value)
            }
        }
    }
}