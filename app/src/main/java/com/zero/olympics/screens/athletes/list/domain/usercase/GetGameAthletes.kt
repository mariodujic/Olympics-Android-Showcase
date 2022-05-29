package com.zero.olympics.screens.athletes.list.domain.usercase

import com.zero.olympics.data.network.result.Result
import com.zero.olympics.screens.athletes.data.repository.AthletesRepository
import com.zero.olympics.screens.athletes.domain.mapper.AthleteNetworkToDomainListMapper
import com.zero.olympics.screens.athletes.domain.mapper.AthleteResultNetworkToDomainListMapper
import com.zero.olympics.screens.athletes.domain.model.GameAthletes
import com.zero.olympics.screens.athletes.domain.model.Medal
import javax.inject.Inject

class GetGameAthletes @Inject constructor(
    private val repository: AthletesRepository,
    private val mapper: AthleteNetworkToDomainListMapper,
    private val athleteResultMapper: AthleteResultNetworkToDomainListMapper
) {

    suspend operator fun invoke(): Result<List<GameAthletes>> {
        val gameResponse = repository.getGames()
        return if (gameResponse is Result.Success) {
            val gameAthletesList = mutableListOf<GameAthletes>()
            val games = gameResponse.value.sortedByDescending { it.year }
            games.forEach { game ->
                if (game.id == null) return@forEach
                val athletesResponse = repository.getGameAthletes(game.id)
                if (athletesResponse is Result.Success) {
                    val athletes = mapper(athletesResponse.value).map {
                        val athleteResults = repository.getAthleteResults(it.id)
                        if (athleteResults is Result.Success) {
                            val results = athleteResultMapper(
                                athleteResults.value.filter { result ->
                                    result.year == game.year
                                })
                            it.copy(results = results)
                        } else it
                    }.sortedByDescending {
                        var score = 0
                        it.results.forEach { result ->
                            score += result.gold * Medal.GOLD.score
                            score += result.silver * Medal.SILVER.score
                            score += result.bronze * Medal.BRONZE.score
                        }
                        score
                    }
                    val gameAthletes = GameAthletes(
                        game = "${game.city} ${game.year}",
                        athletes = athletes
                    )
                    gameAthletesList.add(gameAthletes)
                }
            }
            val gameAthletes = gameAthletesList.filter { it.athletes.isNotEmpty() }
            Result.Success(gameAthletes)
        } else gameResponse as Result<List<GameAthletes>>
    }
}