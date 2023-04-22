package com.example.randomscalemachine.ui.list

import com.example.randomscalemachine.network.SessionService
import com.example.randomscalemachine.persistence.SessionDao
import javax.inject.Inject

class SessionListRepository @Inject constructor(
    private val sessionService: SessionService,
    private val sessionDao: SessionDao
) {
}