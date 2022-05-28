package com.zero.olympics.screens.athletes.domain.mapper

import com.zero.olympics.screens.athletes.data.network.model.AthleteResultNetwork
import com.zero.olympics.screens.athletes.domain.model.AthleteResult
import javax.inject.Inject

class AthleteResultNetworkToDomainListMapper @Inject constructor() {

    operator fun invoke(results: List<AthleteResultNetwork>): List<AthleteResult> {
        return results.map {
            AthleteResult(
                city = it.city.orEmpty(),
                year = it.year,
                gold = it.gold ?: 0,
                silver = it.silver ?: 0,
                bronze = it.bronze ?: 0
            )
        }
    }
}