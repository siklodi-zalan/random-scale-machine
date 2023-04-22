package com.example.randomscalemachine.persistence

import androidx.room.RoomDatabase

abstract class AppDatabase: RoomDatabase() {
    abstract fun posterDao(): SessionDao
}