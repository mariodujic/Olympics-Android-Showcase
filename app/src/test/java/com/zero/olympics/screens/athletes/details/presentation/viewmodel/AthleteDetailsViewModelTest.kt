package com.zero.olympics.screens.athletes.details.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.zero.olympics.data.network.result.Result
import com.zero.olympics.navigation.AthleteDetails
import com.zero.olympics.screens.athletes.details.domain.usecase.GetAthlete
import com.zero.olympics.testutils.FAKE_ATHLETE
import com.zero.olympics.testutils.FAKE_ATHLETE_ID
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class AthleteDetailsViewModelTest {

    @RelaxedMockK
    private lateinit var savedStateHandle: SavedStateHandle

    @RelaxedMockK
    private lateinit var getAthlete: GetAthlete

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var sut: AthleteDetailsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        every { savedStateHandle.get<String>(AthleteDetails.id) }.returns(FAKE_ATHLETE_ID)
        coEvery { getAthlete(FAKE_ATHLETE_ID) }.returns(Result.Success(FAKE_ATHLETE))
        sut = AthleteDetailsViewModel(
            savedStateHandle = savedStateHandle,
            getAthlete = getAthlete
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should return Result Success`() {
        val expectedResult = Result.Success(FAKE_ATHLETE)
        val actualResult = runBlocking { sut.athlete.first() }
        assertEquals(expectedResult, actualResult)
    }
}