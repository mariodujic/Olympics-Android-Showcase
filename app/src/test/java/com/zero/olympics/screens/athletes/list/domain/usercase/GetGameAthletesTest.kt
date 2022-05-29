package com.zero.olympics.screens.athletes.list.domain.usercase

import com.zero.olympics.data.network.result.Result
import com.zero.olympics.screens.athletes.data.repository.AthletesRepository
import com.zero.olympics.screens.athletes.domain.model.GameAthletes
import com.zero.olympics.screens.athletes.list.domain.mapper.ResultsToScoreMapper
import com.zero.olympics.testutils.FAKE_LIST_ATHLETE
import com.zero.olympics.testutils.FAKE_LIST_ATHLETE_RESULTS
import com.zero.olympics.testutils.FAKE_LIST_GAME_NETWORK
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetGameAthletesTest {

    @RelaxedMockK
    private lateinit var repository: AthletesRepository

    private val resultsToScoreMapper = ResultsToScoreMapper()
    private lateinit var sut: GetGameAthletes

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        coEvery { repository.getGames() }.returns(Result.Success(FAKE_LIST_GAME_NETWORK))
        sut = GetGameAthletes(
            repository = repository,
            resultsToScoreMapper = resultsToScoreMapper
        )
    }

    @Test
    fun `should return GameAthletes list Result Success`() {
        FAKE_LIST_GAME_NETWORK.forEach {
            coEvery { repository.getGameAthletes(it.id!!) }.returns(Result.Success(FAKE_LIST_ATHLETE))
        }
        FAKE_LIST_ATHLETE.forEach {
            coEvery { repository.getAthleteResults(it.id) }
                .returns(Result.Success(FAKE_LIST_ATHLETE_RESULTS))
        }
        val expectedList = FAKE_LIST_GAME_NETWORK
            .sortedByDescending { it.year }
            .map { game ->
                val athleteResults = FAKE_LIST_ATHLETE_RESULTS.filter { it.year == game.year }
                val gameAthletes = GameAthletes(
                    game = "${game.city} ${game.year}",
                    athletes = FAKE_LIST_ATHLETE
                        .map { it.copy(results = athleteResults) }
                        .sortedByDescending { resultsToScoreMapper(it.results) }
                )
                gameAthletes
            }
        val expectedResult = Result.Success(expectedList)
        val actualResult = runBlocking { sut() }
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `should return Result Error`() {
        val expectedResult = Result.Error()
        coEvery { repository.getGames() }.returns(expectedResult)
        val actualResult = runBlocking { sut() }
        assertEquals(expectedResult, actualResult)
    }
}