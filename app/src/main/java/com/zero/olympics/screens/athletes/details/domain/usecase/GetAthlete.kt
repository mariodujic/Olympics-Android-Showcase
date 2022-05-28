package com.zero.olympics.screens.athletes.details.domain.usecase

import com.zero.olympics.data.network.result.Result
import com.zero.olympics.screens.athletes.data.repository.AthletesRepository
import com.zero.olympics.screens.athletes.domain.mapper.AthleteNetworkToDomainMapper
import com.zero.olympics.screens.athletes.domain.model.Athlete
import javax.inject.Inject

class GetAthlete @Inject constructor(
    private val repository: AthletesRepository,
    private val mapper: AthleteNetworkToDomainMapper
) {

    suspend operator fun invoke(athleteId: String): Result<Athlete> {
        val result = repository.getAthlete(athleteId)
        return if (result is Result.Success) {
            val athlete = mapper(result.value)
            Result.Success(athlete)
        } else result as Result<Athlete>
    }
}