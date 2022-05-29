package com.zero.olympics.screens.athletes.domain.mapper

import com.zero.olympics.data.network.di.NetworkModule.BASE_URL
import com.zero.olympics.screens.athletes.domain.model.Athlete
import com.zero.olympics.testutils.FAKE_ATHLETE_NETWORK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AthleteNetworkToDomainMapperTest {

    private lateinit var sut: AthleteNetworkToDomainMapper

    @Before
    fun setUp() {
        sut = AthleteNetworkToDomainMapper()
    }

    @Test
    fun `should return Athlete`() {
        val expectedObject = with(FAKE_ATHLETE_NETWORK) {
            Athlete(
                id = id!!,
                name = name.orEmpty(),
                surname = surname.orEmpty(),
                dateOfBirth = dateOfBirth,
                bio = bio.orEmpty(),
                weight = weight,
                height = height,
                photoUrl = BASE_URL + "athletes/${photoId}/photo"
            )
        }
        val actualObject = sut(FAKE_ATHLETE_NETWORK)
        assertEquals(expectedObject, actualObject)
    }
}