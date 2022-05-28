package com.zero.olympics.screens.athletes.domain.mapper

import com.zero.olympics.data.network.di.NetworkModule.BASE_URL
import com.zero.olympics.screens.athletes.data.network.model.AthleteNetwork
import com.zero.olympics.screens.athletes.domain.model.Athlete
import javax.inject.Inject

class AthleteNetworkToDomainMapper @Inject constructor() {

    operator fun invoke(athlete: AthleteNetwork): Athlete {
        return with(athlete) {
            Athlete(
                id = id!!,
                name = name.orEmpty(),
                surname = surname.orEmpty(),
                dateOfBirth = dateOfBirth,
                bio = bio.orEmpty(),
                weight = weight,
                height = height,
                photoUrl = BASE_URL + "athletes/${photoId}/photo"
            )
        }
    }
}