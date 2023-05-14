package com.example.randomscalemachine.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.randomscalemachine.model.Session

@Database(entities = [Session::class], version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {
    abstract fun sessionDao(): SessionDao
}