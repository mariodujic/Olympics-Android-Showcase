package com.zero.olympics.screens.athletes.list.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.zero.olympics.screens.athletes.domain.model.GameAthletes
import com.zero.olympics.screens.common.ui.White
import com.zero.olympics.screens.common.ui.mediumPadding
import com.zero.olympics.screens.common.ui.smallPadding

@Composable
fun AthletesSuccessScreen(
    gameAthletes: List<GameAthletes>,
    navigateDetails: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = smallPadding)
    ) {
        gameAthletes.forEach {
            item {
                Text(
                    text = it.game,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(horizontal = smallPadding)
                )
                LazyRow(
                    contentPadding = PaddingValues(smallPadding),
                    horizontalArrangement = Arrangement.spacedBy(smallPadding)
                ) {
                    items(it.athletes) {
                        Box(modifier = Modifier
                            .size(140.dp)
                            .clickable {
                                navigateDetails(it.id)
                            }) {
                            SubcomposeAsyncImage(
                                model = it.photoUrl, contentDescription = null,
                                loading = {
                                    Box(
                                        modifier = Modifier
                                            .padding(mediumPadding)
                                            .fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                },
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            Text(
                                text = it.name + " " + it.surname,
                                color = White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .fillMaxWidth()
                                    .background(Color.Black.copy(alpha = 0.5f))
                            )
                        }
                    }
                }
            }
        }
    }
}