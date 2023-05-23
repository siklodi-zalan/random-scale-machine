package com.example.randomscalemachine.ui.list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.randomscalemachine.R
import com.example.randomscalemachine.model.GuitarString
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SessionListScreen(viewModel: SessionListViewModel, toMain: () -> Unit) {
    LaunchedEffect(Unit) {
        viewModel.fetchList()
    }
    LazyColumn {
        stickyHeader {
            Row {
                Image(painter = painterResource(id = R.drawable.arrow), contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            toMain()
                            viewModel.fetched.value = false
                        }
                        .padding(horizontal = 7.7.dp)
                        .width(65.dp)
                        .height(65.dp))
                Divider(
                    color = Color.Black,
                    modifier = Modifier
                        .height(70.dp)
                        .width(2.dp)
                )
                Row(modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp)) {
                    GuitarString.values().forEach {
                        Text(text = it.toString(), fontSize = 35.sp,
                            modifier = Modifier.padding(horizontal = 16.dp))
                    }
                }
            }
            Divider(color = Color.Black, modifier = Modifier
                .fillMaxWidth()
                .height(2.dp))
        }

        if(viewModel.fetched.value){
            items(viewModel.sessionList) { session ->
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = relativeTime(session.date), fontSize = 25.sp)
                    Divider(
                        color = Color.Black,
                        modifier = Modifier
                            .height(50.dp)
                            .width(2.dp)
                    )
                    session.results.forEach {
                        Text(text = millToTimeString(it.time), fontSize = 25.sp)
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun relativeTime(dateAsString: String): String {
    val dateAsDate = LocalDate.parse(dateAsString.replace("T00:00:00", ""), DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH))
    val today = LocalDate.now()
    val period = Period.between(dateAsDate, today)

    if(period.days < 1)
        return "Today"

    if(period.days == 1)
        return period.days.toString() + " day"

    if(period.days < 7)
        return period.days.toString() + " days"

    if(period.months < 1)
        return (period.days/7).toString() + " weeks"

    if(period.months == 1)
        return period.months.toString() + " month"

    if(period.years < 1)
        return period.months.toString() + " months"

    if(period.years == 1)
        return period.years.toString() + " year"

    return period.years.toString() + " years"
}

fun millToTimeString(mills: Int): String {
    return "0:" + ((mills/10000)%10).toString() + ((mills/1000)%10).toString()
}