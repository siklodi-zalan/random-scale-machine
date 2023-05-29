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
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var analytics: FirebaseAnalytics

    private val sessionViewModel: SessionViewModel by viewModels()
    private val sessionListViewModel: SessionListViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        analytics = Firebase.analytics
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        sessionViewModel.connectivityManager = cm
        sessionViewModel.analytics = analytics
        sessionListViewModel.connectivityManager = cm

        setContent {
            SessionScreen(sessionViewModel, sessionListViewModel)
        }

        analytics.logEvent(FirebaseAnalytics.Event.APP_OPEN) {
            param("opened_at", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)));
        }
    }
}