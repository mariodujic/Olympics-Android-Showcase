package com.zero.olympics.testutils

import com.zero.olympics.screens.athletes.data.network.model.AthleteNetwork
import com.zero.olympics.screens.athletes.data.network.model.AthleteResultNetwork
import com.zero.olympics.screens.athletes.data.network.model.GameNetwork
import com.zero.olympics.screens.athletes.domain.model.Athlete
import com.zero.olympics.screens.athletes.domain.model.AthleteResult
import com.zero.olympics.screens.athletes.domain.model.GameAthletes

val FAKE_GAME_NETWORK = GameNetwork(id = 1, year = 2010)
val FAKE_LIST_GAME_NETWORK = listOf(
    FAKE_GAME_NETWORK,
    FAKE_GAME_NETWORK.copy(year = 2011),
    FAKE_GAME_NETWORK.copy(year = 2009)
)
val FAKE_ATHLETE_NETWORK = AthleteNetwork(id = "a")
val FAKE_ATHLETE = Athlete(id = "a", name = "b", surname = "c", bio = "d")
val FAKE_LIST_ATHLETE = listOf(FAKE_ATHLETE)
val FAKE_LIST_ATHLETES_NETWORK = listOf(
    FAKE_ATHLETE_NETWORK,
    FAKE_ATHLETE_NETWORK.copy(id = null)
)
val FAKE_LIST_ATHLETE_RESULTS_NETWORK = listOf(AthleteResultNetwork(city = "a"))
val FAKE_ATHLETE_RESULTS = AthleteResult(city = "a", gold = 1, silver = 2, bronze = 1, year = 2010)
val FAKE_LIST_ATHLETE_RESULTS = listOf(
    FAKE_ATHLETE_RESULTS.copy(year = 2011),
    FAKE_ATHLETE_RESULTS.copy(year = 2009)
)
val FAKE_LIST_GAME_ATHLETES = listOf(
    GameAthletes(game = " a", athletes = listOf(FAKE_ATHLETE))
)
const val FAKE_GAME_ID = 1
const val FAKE_ATHLETE_ID = "a"