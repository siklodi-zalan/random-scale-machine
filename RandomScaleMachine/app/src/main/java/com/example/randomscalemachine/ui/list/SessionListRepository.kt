package com.example.randomscalemachine.ui.list

import com.example.randomscalemachine.model.Session
import com.example.randomscalemachine.network.SessionService
import com.example.randomscalemachine.persistence.SessionDao
import javax.inject.Inject

class SessionListRepository @Inject constructor(
    private val sessionService: SessionService,
    private val sessionDao: SessionDao
) {
    suspend fun fetchList(internetAvailable: Boolean): List<Session> {
        var sessionList: MutableList<Session>
        if(internetAvailable){
            sessionList = sessionService.getSessionList() as MutableList<Session>
            sessionDao.insertSessionList(sessionList)
        }
        else
            sessionList = sessionDao.getSessionList() as MutableList<Session>

        sessionList.sortByDescending{it.date}
        return sessionList
    }
}