package com.zero.olympics.features.athletes.data.repository

import com.zero.olympics.data.network.mapper.NetworkResponseMapper
import com.zero.olympics.data.network.result.Result
import com.zero.olympics.features.athletes.data.network.api.AthletesApi
import com.zero.olympics.features.athletes.data.network.model.Game
import javax.inject.Inject

class AthletesRepository @Inject constructor(
    private val api: AthletesApi,
    private val networkResponseMapper: NetworkResponseMapper
) {

    suspend fun getGames(): Result<List<Game>> {
        return networkResponseMapper { api.getGames() }
    }
}