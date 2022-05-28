package com.zero.olympics.screens.athletes.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GameNetwork(
    @SerializedName("game_id") val id: Int? = null,
    val city: String? = null,
    val year: Int? = null
)