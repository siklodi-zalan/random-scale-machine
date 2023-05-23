package com.example.randomscalemachine.ui.session

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.randomscalemachine.model.Result
import com.example.randomscalemachine.model.Session
import com.example.randomscalemachine.network.SessionService
import com.example.randomscalemachine.persistence.SessionDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

class SessionRepository @Inject constructor(
    private val sessionService: SessionService,
    private val sessionDao: SessionDao
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun saveSession(results: List<Result>, internetAvailable: Boolean)  = withContext(
        Dispatchers.IO) {
        if(internetAvailable)
            sessionService.addSession(Session(-1, results, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH))))
        else
            sessionDao.insertSession(Session(0, results, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH))))
    }
}