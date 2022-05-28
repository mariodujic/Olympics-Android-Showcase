package com.zero.olympics.screens.athletes.domain.usercase

import com.zero.olympics.data.network.result.Result
import com.zero.olympics.screens.athletes.data.repository.AthletesRepository
import com.zero.olympics.screens.athletes.domain.mapper.AthleteNetworkToDomainListMapper
import com.zero.olympics.screens.athletes.domain.model.GameAthletes
import javax.inject.Inject

class GetGameAthletes @Inject constructor(
    private val repository: AthletesRepository,
    private val mapper: AthleteNetworkToDomainListMapper
) {

    suspend operator fun invoke(): Result<List<GameAthletes>> {
        val gameResponse = repository.getGames()
        return if (gameResponse is Result.Success) {
            val gameAthletesList = mutableListOf<GameAthletes>()
            val games = gameResponse.value.sortedByDescending { it.year }
            games.forEach { game ->
                game.id?.let {
                    val athletesResponse = repository.getGameAthletes(it)
                    if (athletesResponse is Result.Success) {
                        val gameAthletes = GameAthletes(
                            game = game.city + " " + game.year,
                            athletes = mapper(athletesResponse.value)
                        )
                        gameAthletesList.add(gameAthletes)
                    }
                }
            }
            val gameAthletes = gameAthletesList.filter { it.athletes.isNotEmpty() }
            Result.Success(gameAthletes)
        } else gameResponse as Result<List<GameAthletes>>
    }
}