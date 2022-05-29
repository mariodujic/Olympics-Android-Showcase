package com.zero.olympics.screens.athletes.domain.mapper

import com.zero.olympics.screens.athletes.domain.model.AthleteResult
import com.zero.olympics.testutils.FAKE_LIST_ATHLETE_RESULTS_NETWORK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AthleteResultNetworkToDomainListMapperTest {

    private lateinit var sut: AthleteResultNetworkToDomainListMapper

    @Before
    fun setUp() {
        sut = AthleteResultNetworkToDomainListMapper()
    }

    @Test
    fun `should return AthleteResult list`() {
        val expectedList = FAKE_LIST_ATHLETE_RESULTS_NETWORK.map {
            AthleteResult(
                city = it.city.orEmpty(),
                year = it.year,
                gold = it.gold ?: 0,
                silver = it.silver ?: 0,
                bronze = it.bronze ?: 0
            )
        }
        val actualList = sut(FAKE_LIST_ATHLETE_RESULTS_NETWORK)
        assertEquals(expectedList, actualList)
    }
}