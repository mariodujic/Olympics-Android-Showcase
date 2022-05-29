package com.zero.olympics.screens.athletes.data.repository

import com.zero.olympics.data.network.mapper.NetworkResponseMapper
import com.zero.olympics.screens.athletes.data.network.api.AthletesApi
import com.zero.olympics.screens.athletes.domain.mapper.AthleteNetworkToDomainListMapper
import com.zero.olympics.screens.athletes.domain.mapper.AthleteNetworkToDomainMapper
import com.zero.olympics.screens.athletes.domain.mapper.AthleteResultNetworkToDomainListMapper
import com.zero.olympics.testutils.*
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class AthletesRepositoryTest {

    @RelaxedMockK
    private lateinit var api: AthletesApi

    @RelaxedMockK
    private lateinit var athleteListMapper: AthleteNetworkToDomainListMapper

    @RelaxedMockK
    private lateinit var athleteMapper: AthleteNetworkToDomainMapper

    @RelaxedMockK
    private lateinit var athleteResultMapper: AthleteResultNetworkToDomainListMapper

    private val networkResponseMapper = NetworkResponseMapper()
    private lateinit var sut: AthletesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        sut = AthletesRepository(
            api = api,
            networkResponseMapper = networkResponseMapper,
            athleteListMapper = athleteListMapper,
            athleteMapper = athleteMapper,
            athleteResultMapper = athleteResultMapper
        )
    }

    @Test
    fun `should call getGames once`() {
        val fakeResponse = Response.success(FAKE_LIST_GAME_NETWORK)
        coEvery { api.getGames() }.returns(fakeResponse)
        runBlocking { sut.getGames() }
        coVerify { api.getGames() }
    }

    @Test
    fun `should call getGameAthletes and athleteListMapper once with correct arguments`() {
        val fakeResponse = Response.success(FAKE_LIST_ATHLETES_NETWORK)
        coEvery { api.getGameAthletes(FAKE_GAME_ID) }.returns(fakeResponse)
        runBlocking { sut.getGameAthletes(FAKE_GAME_ID) }
        coVerify { api.getGameAthletes((FAKE_GAME_ID)) }
        coVerify { athleteListMapper(FAKE_LIST_ATHLETES_NETWORK) }
    }

    @Test
    fun `should call getAthlete and athleteMapper once with correct arguments`() {
        val fakeResponse = Response.success(FAKE_ATHLETE_NETWORK)
        coEvery { api.getAthlete(FAKE_ATHLETE_ID) }.returns(fakeResponse)
        runBlocking { sut.getAthlete(FAKE_ATHLETE_ID) }
        coVerify { api.getAthlete(FAKE_ATHLETE_ID) }
        coVerify { athleteMapper(FAKE_ATHLETE_NETWORK) }
    }

    @Test
    fun `should call getAthleteResults and athleteResultMapper once with correct arguments`() {
        val fakeResponse = Response.success(FAKE_LIST_ATHLETE_RESULTS_NETWORK)
        coEvery { api.getAthleteResults(FAKE_ATHLETE_ID) }.returns(fakeResponse)
        runBlocking { sut.getAthleteResults(FAKE_ATHLETE_ID) }
        coVerify { api.getAthleteResults(FAKE_ATHLETE_ID) }
        coVerify { athleteResultMapper(FAKE_LIST_ATHLETE_RESULTS_NETWORK) }
    }
}