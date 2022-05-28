package com.zero.olympics.screens.athletes.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Athlete(
    @SerializedName("athlete_id") val id: String? = null,
    val name: String? = null,
    val surname: String? = null,
    val dateOfBirth: String? = null,
    val bio: String? = null,
    val weight: Int? = null,
    val height: Int? = null,
    @SerializedName("photo_id") val photoId: String? = null
)