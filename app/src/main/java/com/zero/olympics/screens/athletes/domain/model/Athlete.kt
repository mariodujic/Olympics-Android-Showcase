package com.zero.olympics.screens.athletes.domain.model

data class Athlete(
    val id: String,
    val name: String,
    val surname: String,
    val dateOfBirth: String? = null,
    val bio: String,
    val weight: Int? = null,
    val height: Int? = null,
    val photoUrl: String? = null
)