package com.zero.olympics.screens.athletes.details.domain.usecase

import com.zero.olympics.data.network.result.Result
import com.zero.olympics.screens.athletes.data.repository.AthletesRepository
import com.zero.olympics.testutils.FAKE_ATHLETE
import com.zero.olympics.testutils.FAKE_ATHLETE_ID
import com.zero.olympics.testutils.FAKE_LIST_ATHLETE_RESULTS
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetAthleteTest {

    @RelaxedMockK
    private lateinit var repository: AthletesRepository

    private lateinit var sut: GetAthlete

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        sut = GetAthlete(
            repository = repository
        )
    }

    @Test
    fun `should call getAthlete once with correct argument`() {
        runBlocking { sut(FAKE_ATHLETE_ID) }
        coVerify { repository.getAthlete(FAKE_ATHLETE_ID) }
    }

    @Test
    fun `should call getAthleteResults once with correct argument`() {
        val fakeResult = Result.Success(FAKE_ATHLETE)
        coEvery { repository.getAthlete(FAKE_ATHLETE_ID) }.returns(fakeResult)
        runBlocking { sut(FAKE_ATHLETE_ID) }
        coVerify { repository.getAthleteResults(FAKE_ATHLETE_ID) }
    }

    @Test
    fun `should return Result Success with athlete results`() {
        val fakeResult = Result.Success(FAKE_ATHLETE)
        coEvery { repository.getAthlete(FAKE_ATHLETE_ID) }.returns(fakeResult)
        val fakeAthleteResults = Result.Success(FAKE_LIST_ATHLETE_RESULTS)
        coEvery { repository.getAthleteResults(FAKE_ATHLETE_ID) }.returns(fakeAthleteResults)
        val expectedResult = Result.Success(FAKE_ATHLETE.copy(results = FAKE_LIST_ATHLETE_RESULTS))
        val actualResult = runBlocking { sut(FAKE_ATHLETE_ID) }
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `should return Result Success without athlete results`() {
        val fakeResult = Result.Success(FAKE_ATHLETE)
        coEvery { repository.getAthlete(FAKE_ATHLETE_ID) }.returns(fakeResult)
        val fakeAthleteResults = Result.Error()
        coEvery { repository.getAthleteResults(FAKE_ATHLETE_ID) }.returns(fakeAthleteResults)
        val expectedResult = Result.Success(FAKE_ATHLETE)
        val actualResult = runBlocking { sut(FAKE_ATHLETE_ID) }
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `should return Result Error`() {
        val expectedResult = Result.Error()
        coEvery { repository.getAthlete(FAKE_ATHLETE_ID) }.returns(expectedResult)
        val actualResult = runBlocking { sut(FAKE_ATHLETE_ID) }
        assertEquals(expectedResult, actualResult)
    }
}