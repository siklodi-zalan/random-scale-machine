package com.example.randomscalemachine.ui.session

import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomscalemachine.model.GuitarString
import com.example.randomscalemachine.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.randomscalemachine.model.Result
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val sessionRepository: SessionRepository
) : ViewModel() {
    lateinit var connectivityManager: ConnectivityManager
    lateinit var analytics: FirebaseAnalytics

    var screenState: MutableState<ScreenState>  = mutableStateOf(ScreenState.Main)
    var count: MutableState<Int> = mutableStateOf(0)
    var notes: MutableState<Array<Note>> = mutableStateOf(emptyArray())
    private var results: List<Result> = mutableListOf()

    fun toMain() {
        screenState.value = ScreenState.Main
    }

    fun start() {
        screenState.value = ScreenState.Practice
    }

    fun generateExercise() {
        notes.value = enumValues()
        notes.value.shuffle()

        if(count.value != 5)
            count.value++
    }

    fun saveResult(millisec: Int) {
        results = results + Result(string = GuitarString.values()[count.value - 1], time = millisec)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveSession() {
        val activeNetworkInfo = connectivityManager.getActiveNetworkInfo()
        viewModelScope.launch {
            sessionRepository.saveSession(results = results, activeNetworkInfo != null && activeNetworkInfo.isConnected())
            analytics.logEvent("session_saved") {
                param("opened_at", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)));
                param("internet_available", (activeNetworkInfo != null && activeNetworkInfo.isConnected()).toString())
            }
        }
        reset()
    }

    fun reset() {
        notes.value = emptyArray()
        count.value = 0
    }

    fun seeList(){
        screenState.value = ScreenState.List
    }
}