package com.zero.olympics.screens.athletes.details.domain.usecase

import com.zero.olympics.data.network.result.Result
import com.zero.olympics.screens.athletes.data.repository.AthletesRepository
import com.zero.olympics.screens.athletes.domain.model.Athlete
import javax.inject.Inject

class GetAthlete @Inject constructor(private val repository: AthletesRepository) {

    suspend operator fun invoke(athleteId: String): Result<Athlete> {
        return when(val result = repository.getAthlete(athleteId)) {
            is Result.Success -> {
                val athlete = result.value
                val athleteResults = repository.getAthleteResults(athleteId)
                Result.Success(
                    if (athleteResults is Result.Success) {
                        athlete.copy(results = athleteResults.value)
                    } else {
                        athlete
                    }
                )
            }
            else ->result
        }
    }
}