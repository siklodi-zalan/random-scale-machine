package com.example.randomscalemachine.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.randomscalemachine.model.Session

@Dao
interface SessionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSession(session: Session)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSessionList(sessions: List<Session>)

    @Query("SELECT * FROM Session WHERE id = :id_")
    fun getSession(id_: String): Session

    @Query("SELECT * FROM Session")
    fun getSessionList(): List<Session>

    @Query("DELETE FROM Session")
    fun deleteAll()
}