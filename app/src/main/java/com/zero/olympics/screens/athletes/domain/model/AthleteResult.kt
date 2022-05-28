package com.zero.olympics.screens.athletes.domain.model

data class AthleteResult(
    val city: String,
    val year: Int? = null,
    val gold: Int,
    val silver: Int,
    val bronze: Int
)