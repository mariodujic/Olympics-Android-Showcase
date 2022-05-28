package com.zero.olympics.screens.athletes.data.network.api

import com.zero.olympics.screens.athletes.data.network.model.AthleteNetwork
import com.zero.olympics.screens.athletes.data.network.model.GameNetwork
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AthletesApi {

    @GET("/games")
    suspend fun getGames(): Response<List<GameNetwork>>

    @GET("/games/{gameId}/athletes")
    suspend fun getGameAthletes(@Path("gameId") gameId: Int): Response<List<AthleteNetwork>>
}