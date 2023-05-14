package com.example.randomscalemachine.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.randomscalemachine.model.Session

@Dao
interface SessionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: Session)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSessionList(sessions: List<Session>)

    @Query("SELECT * FROM Session WHERE id = :id_")
    suspend fun getSession(id_: Long): Session?

    @Query("SELECT * FROM Session")
    suspend fun getSessionList(): List<Session>
}