package com.example.randomscalemachine.network

import com.example.randomscalemachine.model.Session
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SessionService {
    @POST("session")
    suspend fun addSession(@Body session: Session): String
    @GET("session")
    suspend fun getSessionList(): List<Session>
}