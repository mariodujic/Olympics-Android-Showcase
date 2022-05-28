package com.zero.olympics.features.athletes.data.network.api

import com.zero.olympics.features.athletes.data.network.model.Game
import retrofit2.Response
import retrofit2.http.GET

interface AthletesApi {

    @GET("/games")
    suspend fun getGames(): Response<List<Game>>
}