package com.example.randomscalemachine.ui.list

import android.net.ConnectivityManager
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.randomscalemachine.model.Session
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SessionListViewModel @Inject constructor(
    private val sessionListRepository: SessionListRepository
) : ViewModel() {
    lateinit var connectivityManager: ConnectivityManager

    var sessionList: List<Session> = mutableListOf()
    var fetched: MutableState<Boolean> = mutableStateOf(false)

    suspend fun fetchList() = withContext(Dispatchers.IO){
        val activeNetworkInfo = connectivityManager.getActiveNetworkInfo()
        sessionList = sessionListRepository.fetchList(activeNetworkInfo != null && activeNetworkInfo.isConnected())
        fetched.value = true
    }
}