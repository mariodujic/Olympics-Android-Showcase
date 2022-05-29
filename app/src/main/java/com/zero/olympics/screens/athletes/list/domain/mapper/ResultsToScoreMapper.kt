package com.zero.olympics.screens.athletes.list.domain.mapper

import com.zero.olympics.screens.athletes.domain.model.AthleteResult
import com.zero.olympics.screens.athletes.domain.model.Medal
import javax.inject.Inject

class ResultsToScoreMapper @Inject constructor() {

    operator fun invoke(results: List<AthleteResult>): Int {
        return results.sumOf {
            it.gold * Medal.GOLD.score + it.silver * Medal.SILVER.score + it.bronze * Medal.BRONZE.score
        }
    }
}