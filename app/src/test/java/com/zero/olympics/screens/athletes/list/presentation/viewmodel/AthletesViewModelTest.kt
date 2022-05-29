package com.zero.olympics.screens.athletes.list.presentation.viewmodel

import com.zero.olympics.data.network.result.Result
import com.zero.olympics.screens.athletes.list.domain.usercase.GetGameAthletes
import com.zero.olympics.testutils.FAKE_LIST_GAME_ATHLETES
import io.mockk.MockKAnnotations
import io.mockk.coEvery
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
class AthletesViewModelTest {

    @RelaxedMockK
    private lateinit var getGameAthletes: GetGameAthletes

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var sut: AthletesViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        coEvery { getGameAthletes() }.returns(Result.Success(FAKE_LIST_GAME_ATHLETES))
        sut = AthletesViewModel(getGameAthletes = getGameAthletes)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should return GameAthletes list Result Success`() {
        val expectedResult = Result.Success(FAKE_LIST_GAME_ATHLETES)
        val actualResult = runBlocking { sut.games.first() }
        assertEquals(expectedResult, actualResult)
    }
}