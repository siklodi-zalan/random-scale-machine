package com.example.randomscalemachine.ui.list

import androidx.lifecycle.ViewModel
import com.example.randomscalemachine.ui.session.SessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SessionListViewModel @Inject constructor(
    sessionListRepository: SessionListRepository
) : ViewModel() {
}