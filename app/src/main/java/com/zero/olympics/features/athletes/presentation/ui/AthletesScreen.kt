package com.zero.olympics.features.athletes.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.zero.olympics.data.network.result.Result
import com.zero.olympics.features.athletes.presentation.viewmodel.AthletesViewModel
import com.zero.olympics.features.common.ui.mediumPadding

@Composable
fun AthletesScreen() {

    val viewModel: AthletesViewModel = hiltViewModel()
    val gamesResult by viewModel.games.collectAsState()

    Box(
        modifier = Modifier
            .padding(mediumPadding)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val result = gamesResult) {
            Result.Empty -> Text(text = "No games found.")
            is Result.Error -> Text(text = "Error retrieving data, try again later.")
            Result.Loading -> CircularProgressIndicator()
            is Result.Success -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(result.value) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            it.city?.let { city ->
                                Text(text = city, style = MaterialTheme.typography.h5)
                                Text(text = it.year.toString(), style = MaterialTheme.typography.h5)
                            }
                        }
                    }
                }
            }
        }
    }
}