package com.zero.olympics.screens.athletes.domain.mapper

import com.zero.olympics.screens.athletes.data.network.model.AthleteNetwork
import com.zero.olympics.screens.athletes.domain.model.Athlete
import javax.inject.Inject

class AthleteNetworkToDomainListMapper @Inject constructor(
    private val mapper: AthleteNetworkToDomainMapper
) {

    operator fun invoke(athletes: List<AthleteNetwork>): List<Athlete> {
        return athletes
            .filter { it.id != null }
            .map { mapper(it) }
    }
}