package com.zero.olympics.screens.athletes.data.network.model

import androidx.annotation.Keep

@Keep
data class AthleteResultNetwork(
    val city: String? = null,
    val year: Int? = null,
    val gold: Int? = null,
    val silver: Int? = null,
    val bronze: Int? = null
)