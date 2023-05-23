package com.example.randomscalemachine.ui.session

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.randomscalemachine.model.GuitarString
import com.example.randomscalemachine.ui.list.SessionListScreen
import com.example.randomscalemachine.ui.list.SessionListViewModel
import kotlinx.coroutines.delay

enum class ScreenState {
    Main, Practice, List
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SessionScreen(sessionViewModel: SessionViewModel, sessionListViewModel: SessionListViewModel) {
    when (sessionViewModel.screenState.value) {
        ScreenState.Main -> MainScreen(sessionViewModel)
        ScreenState.Practice -> PracticeScreen(sessionViewModel) { sessionViewModel.seeList() }
        ScreenState.List -> SessionListScreen(sessionListViewModel) { sessionViewModel.toMain() }
    }
}

@Composable
fun MainScreen(sessionViewModel: SessionViewModel) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 65.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = { sessionViewModel.start() }
        ) {
            Text(text = "Start")
        }
        Button(onClick = { sessionViewModel.seeList() }
        ) {
            Text(text = "See list")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PracticeScreen(sessionViewModel: SessionViewModel, toList: () -> Unit) {
    var startTime by remember {
        mutableStateOf(0L)
    }

    var timeElapsed by remember {
        mutableStateOf(0L)
    }

    var isTimerRunning by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = isTimerRunning) {
        while(isTimerRunning) {
            delay(1)
            timeElapsed = System.currentTimeMillis() - startTime
        }
    }
    Column {
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 65.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                if(isTimerRunning) {
                    isTimerRunning = false
                    sessionViewModel.saveResult(timeElapsed.toInt())
                }
                else {
                    if(sessionViewModel.count.value == 5) {
                        isTimerRunning = false
                        sessionViewModel.saveSession()
                        timeElapsed = 0
                        toList()
                    }
                    else {
                        isTimerRunning = true
                        sessionViewModel.generateExercise()
                        startTime = System.currentTimeMillis()
                    }
                }
            }
            ) {
                if(isTimerRunning)
                    Text(text = "Stop")
                else if(sessionViewModel.count.value == 5)
                    Text(text = "Done")
                else
                    Text(text = "Start")
            }
            Button(onClick = {
                isTimerRunning = false
                sessionViewModel.reset()
                timeElapsed = 0
            }
            ) {
                Text(text = "Restart")
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Divider(color = Color.Black, modifier = Modifier
            .fillMaxWidth()
            .width(1.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                if(sessionViewModel.count.value == 0)
                    Text(text = "E", fontSize = 50.sp)
                else
                    Text(text = GuitarString.values()[sessionViewModel.count.value - 1].toString(),
                        fontSize = 50.sp)
                Spacer(modifier = Modifier.height(20.dp))
                Divider(color = Color.Black, modifier = Modifier
                    .fillMaxWidth(fraction = 0.48f)
                    .width(1.dp))
                Spacer(modifier = Modifier.height(150.dp))
                Text(text = "00:" + ((timeElapsed/10000)%10).toString() + ((timeElapsed/1000)%10).toString() + ":" + ((timeElapsed/100)%10).toString() + ((timeElapsed/10)%10).toString(),
                    fontSize = 40.sp)
            }
            Divider(color = Color.Black, modifier = Modifier
                .fillMaxHeight()
                .width(1.dp))
            Column(modifier = Modifier
                .padding(horizontal = 70.dp, vertical = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                sessionViewModel.notes.value.forEach  { note ->
                    Text(text = note.toString().replace("Sharp", "#"),
                        fontSize = 30.sp)
                }
            }
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun SessionScreenPreview() {
    SessionScreen(viewModel = viewModel())
}*/