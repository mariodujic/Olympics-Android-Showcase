package com.zero.olympics.screens.athletes.list.domain.usercase

import com.zero.olympics.data.network.result.Result
import com.zero.olympics.screens.athletes.data.repository.AthletesRepository
import com.zero.olympics.screens.athletes.domain.model.GameAthletes
import com.zero.olympics.screens.athletes.list.domain.mapper.ResultsToScoreMapper
import javax.inject.Inject

class GetGameAthletes @Inject constructor(
    private val repository: AthletesRepository,
    private val resultsToScoreMapper: ResultsToScoreMapper
) {

    suspend operator fun invoke(): Result<List<GameAthletes>> {
        val gameResult = repository.getGames()
        return if (gameResult is Result.Success) {
            val gameAthletesList = mutableListOf<GameAthletes>()
            val games = gameResult.value.sortedByDescending { it.year }
            games.forEach { game ->
                if (game.id == null) return@forEach
                val athletesResult = repository.getGameAthletes(game.id)
                if (athletesResult is Result.Success) {
                    val athletes = athletesResult.value.map {
                        val athleteResults = repository.getAthleteResults(it.id)
                        if (athleteResults is Result.Success) {
                            val results = athleteResults.value.filter { result ->
                                result.year == game.year
                            }
                            it.copy(results = results)
                        } else it
                    }.sortedByDescending { resultsToScoreMapper(it.results) }
                    val gameAthletes = GameAthletes(
                        game = "${game.city} ${game.year}",
                        athletes = athletes
                    )
                    gameAthletesList.add(gameAthletes)
                }
            }
            val gameAthletes = gameAthletesList.filter { it.athletes.isNotEmpty() }
            Result.Success(gameAthletes)
        } else Result.Error()
    }
}