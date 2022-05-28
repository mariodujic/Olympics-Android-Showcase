package com.zero.olympics.features.athletes.data.network.di

import com.zero.olympics.features.athletes.data.network.api.AthletesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AthletesNetworkModule {

    @Provides
    @Singleton
    fun provideAthletesApi(retrofit: Retrofit): AthletesApi {
        return retrofit.create(AthletesApi::class.java)
    }
}