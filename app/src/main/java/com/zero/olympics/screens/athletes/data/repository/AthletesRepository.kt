package com.zero.olympics.screens.athletes.data.repository

import com.zero.olympics.data.network.mapper.NetworkResponseMapper
import com.zero.olympics.data.network.result.Result
import com.zero.olympics.screens.athletes.data.network.api.AthletesApi
import com.zero.olympics.screens.athletes.data.network.model.GameNetwork
import com.zero.olympics.screens.athletes.domain.mapper.AthleteNetworkToDomainListMapper
import com.zero.olympics.screens.athletes.domain.mapper.AthleteNetworkToDomainMapper
import com.zero.olympics.screens.athletes.domain.mapper.AthleteResultNetworkToDomainListMapper
import com.zero.olympics.screens.athletes.domain.model.Athlete
import com.zero.olympics.screens.athletes.domain.model.AthleteResult
import javax.inject.Inject

class AthletesRepository @Inject constructor(
    private val api: AthletesApi,
    private val networkResponseMapper: NetworkResponseMapper,
    private val athleteListMapper: AthleteNetworkToDomainListMapper,
    private val athleteMapper: AthleteNetworkToDomainMapper,
    private val athleteResultMapper: AthleteResultNetworkToDomainListMapper
) {

    suspend fun getGames(): Result<List<GameNetwork>> {
        return networkResponseMapper(response = { api.getGames() })
    }

    suspend fun getGameAthletes(gameId: Int): Result<List<Athlete>> {
        return networkResponseMapper(
            response = { api.getGameAthletes(gameId) },
            successDataMapper = { athleteListMapper(it) }
        )
    }

    suspend fun getAthlete(athleteId: String): Result<Athlete> {
        return networkResponseMapper(
            response = { api.getAthlete(athleteId) },
            successDataMapper = { athleteMapper(it) }
        )
    }

    suspend fun getAthleteResults(athleteId: String): Result<List<AthleteResult>> {
        return networkResponseMapper(
            response = { api.getAthleteResults(athleteId) },
            successDataMapper = { athleteResultMapper(it) }
        )
    }
}