package com.example.randomscalemachine.di

import com.example.randomscalemachine.network.SessionService
import com.example.randomscalemachine.persistence.SessionDao
import com.example.randomscalemachine.ui.list.SessionListRepository
import com.example.randomscalemachine.ui.session.SessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideSessionRepository(
        sessionService: SessionService,
        sessionDao: SessionDao
    ): SessionRepository {
        return SessionRepository(sessionService, sessionDao)
    }

    @Provides
    @ViewModelScoped
    fun provideSessionListRepository(
        sessionService: SessionService,
        sessionDao: SessionDao
    ): SessionListRepository {
        return SessionListRepository(sessionService, sessionDao)
    }
}