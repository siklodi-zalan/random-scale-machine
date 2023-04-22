package com.example.randomscalemachine.di

import com.example.randomscalemachine.network.SessionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideSessionService(retrofit: Retrofit): SessionService {
        return retrofit.create(SessionService::class.java)
    }
}