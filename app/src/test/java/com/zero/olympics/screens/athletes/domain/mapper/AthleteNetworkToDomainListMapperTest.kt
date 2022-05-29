package com.zero.olympics.screens.athletes.domain.mapper

import com.zero.olympics.testutils.FAKE_LIST_ATHLETES_NETWORK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AthleteNetworkToDomainListMapperTest {

    private val mapper = AthleteNetworkToDomainMapper()
    private lateinit var sut: AthleteNetworkToDomainListMapper

    @Before
    fun setUp() {
        sut = AthleteNetworkToDomainListMapper(mapper)
    }

    @Test
    fun `should return Athlete list`() {
        val expectedList = FAKE_LIST_ATHLETES_NETWORK
            .filter { it.id != null }
            .map { mapper(it) }
        val actualList = sut(FAKE_LIST_ATHLETES_NETWORK)
        assertEquals(expectedList, actualList)
    }
}