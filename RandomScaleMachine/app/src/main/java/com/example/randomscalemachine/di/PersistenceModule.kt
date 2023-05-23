package com.example.randomscalemachine.di

import android.app.Application
import androidx.room.Room
import com.example.randomscalemachine.persistence.AppDatabase
import com.example.randomscalemachine.persistence.Converters
import com.example.randomscalemachine.persistence.SessionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(
                application,
                AppDatabase::class.java,
                "SessionDatabase.db"
            )
            .addTypeConverter(Converters())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideSessionDao(appDatabase: AppDatabase): SessionDao {
        return appDatabase.sessionDao()
    }
}