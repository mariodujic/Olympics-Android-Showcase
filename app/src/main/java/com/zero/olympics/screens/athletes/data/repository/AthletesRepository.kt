package com.zero.olympics.screens.athletes.data.repository

import com.zero.olympics.data.network.mapper.NetworkResponseMapper
import com.zero.olympics.data.network.result.Result
import com.zero.olympics.screens.athletes.data.network.api.AthletesApi
import com.zero.olympics.screens.athletes.data.network.model.AthleteNetwork
import com.zero.olympics.screens.athletes.data.network.model.AthleteResultNetwork
import com.zero.olympics.screens.athletes.data.network.model.GameNetwork
import com.zero.olympics.screens.athletes.domain.model.AthleteResult
import javax.inject.Inject

class AthletesRepository @Inject constructor(
    private val api: AthletesApi,
    private val networkResponseMapper: NetworkResponseMapper
) {

    suspend fun getGames(): Result<List<GameNetwork>> {
        return networkResponseMapper { api.getGames() }
    }

    suspend fun getGameAthletes(gameId: Int): Result<List<AthleteNetwork>> {
        return networkResponseMapper { api.getGameAthletes(gameId) }
    }

    suspend fun getAthlete(athleteId: String): Result<AthleteNetwork> {
        return networkResponseMapper { api.getAthlete(athleteId) }
    }

    suspend fun getAthleteResults(athleteId: String): Result<List<AthleteResultNetwork>> {
        return networkResponseMapper { api.getAthleteResults(athleteId) }
    }
}