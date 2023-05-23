package com.example.randomscalemachine.ui.session

import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.example.randomscalemachine.ui.list.SessionListViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.content.Context

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val sessionViewModel: SessionViewModel by viewModels()
    private val sessionListViewModel: SessionListViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        sessionViewModel.connectivityManager = cm
        sessionListViewModel.connectivityManager = cm

        setContent {
            SessionScreen(sessionViewModel, sessionListViewModel)
        }
    }
}