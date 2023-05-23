package com.example.randomscalemachine.persistence

import androidx.room.Database
import androidx.room.ProvidedTypeConverter
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.randomscalemachine.model.Result
import com.example.randomscalemachine.model.Session
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Database(entities = [Session::class], version = 2, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun sessionDao(): SessionDao
}

@ProvidedTypeConverter
class Converters {
    @TypeConverter
    fun toResultJson(results: List<Result>) : String {
        return Gson().toJson(
            results,
            object : TypeToken<ArrayList<Result>>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromResultsJson(json: String): List<Result>{
        return Gson().fromJson<ArrayList<Result>>(
            json,
            object: TypeToken<ArrayList<Result>>(){}.type
        ) ?: emptyList()
    }
}