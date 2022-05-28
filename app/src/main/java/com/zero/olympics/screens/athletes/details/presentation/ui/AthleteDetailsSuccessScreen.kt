package com.zero.olympics.screens.athletes.details.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.halilibo.richtext.markdown.Markdown
import com.halilibo.richtext.ui.RichText
import com.zero.olympics.R
import com.zero.olympics.screens.athletes.domain.model.Athlete
import com.zero.olympics.screens.common.ui.mediumPadding
import com.zero.olympics.screens.common.ui.smallPadding
import com.zero.olympics.screens.common.ui.smallerPadding

@Composable
fun AthleteDetailsSuccessScreen(athlete: Athlete) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(smallPadding),
            modifier = Modifier
                .height(200.dp)
                .padding(smallPadding)
        ) {
            SubcomposeAsyncImage(
                model = athlete.photoUrl, contentDescription = null,
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
                modifier = Modifier.weight(1f)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(smallerPadding),
                modifier = Modifier.weight(1f)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(smallerPadding)) {
                    Text(
                        text = stringResource(id = R.string.name_label),
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "${athlete.name} ${athlete.surname}")
                }
                Row(horizontalArrangement = Arrangement.spacedBy(smallerPadding)) {
                    Text(
                        text = stringResource(id = R.string.dob_label),
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "${athlete.dateOfBirth}")
                }
                Row(horizontalArrangement = Arrangement.spacedBy(smallerPadding)) {
                    Text(
                        text = stringResource(id = R.string.weight_label),
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "${athlete.weight}kg")
                }
                Row(horizontalArrangement = Arrangement.spacedBy(smallerPadding)) {
                    Text(
                        text = stringResource(id = R.string.height_label),
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "${athlete.height}cm")
                }
            }
        }
        Text(
            text = stringResource(id = R.string.bio_label),
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(horizontal = smallPadding)
        )
        RichText(modifier = Modifier.padding(smallPadding)) {
            Markdown(content = athlete.bio)
        }
    }
}