package com.zero.olympics.screens.athletes.list.domain.mapper

import com.zero.olympics.screens.athletes.domain.model.Medal
import com.zero.olympics.testutils.FAKE_LIST_ATHLETE_RESULTS
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ResultsToScoreMapperTest {

    private lateinit var sut: ResultsToScoreMapper

    @Before
    fun setUp() {
        sut = ResultsToScoreMapper()
    }

    @Test
    fun `should return athlete results score`() {
        val expectedResult = FAKE_LIST_ATHLETE_RESULTS.sumOf {
            it.gold * Medal.GOLD.score + it.silver * Medal.SILVER.score + it.bronze * Medal.BRONZE.score
        }
        val actualResult = sut(FAKE_LIST_ATHLETE_RESULTS)
        assertEquals(expectedResult, actualResult)
    }
}