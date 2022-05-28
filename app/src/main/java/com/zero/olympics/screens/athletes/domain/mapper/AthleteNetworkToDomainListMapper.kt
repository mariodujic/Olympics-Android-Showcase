package com.zero.olympics.screens.athletes.domain.mapper

import com.zero.olympics.data.network.di.NetworkModule.BASE_URL
import com.zero.olympics.screens.athletes.data.network.model.AthleteNetwork
import com.zero.olympics.screens.athletes.domain.model.Athlete
import javax.inject.Inject

class AthleteNetworkToDomainListMapper @Inject constructor() {

    operator fun invoke(athletes: List<AthleteNetwork>): List<Athlete> {
        return athletes.map {
            with(it) {
                Athlete(
                    id = id,
                    name = name.orEmpty(),
                    surname = surname.orEmpty(),
                    dateOfBirth = dateOfBirth,
                    bio = bio,
                    weight = weight,
                    height = height,
                    photoUrl = BASE_URL + "athletes/${photoId}/photo"
                )
            }
        }
    }
}