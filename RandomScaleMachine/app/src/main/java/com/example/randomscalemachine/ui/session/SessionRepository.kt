package com.example.randomscalemachine.ui.session

import com.example.randomscalemachine.network.SessionService
import com.example.randomscalemachine.persistence.SessionDao
import javax.inject.Inject

class SessionRepository @Inject constructor(
    private val sessionService: SessionService,
    private val sessionDao: SessionDao
) {
}