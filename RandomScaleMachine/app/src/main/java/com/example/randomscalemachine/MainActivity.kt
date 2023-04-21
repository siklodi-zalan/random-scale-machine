package com.example.randomscalemachine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContent {
            RandomScaleMachineTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }*/
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    /*RandomScaleMachineTheme {
        Greeting("Android")
    }*/
}