package com.example.stopwatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stopwatch.ui.theme.StopWatchTheme
import kotlinx.coroutines.delay
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StopWatchTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StopWatchApp()
                }
            }
        }
    }
}

@Composable
fun StopWatchApp() {
    var time by remember { mutableStateOf(0) }
    var timerRunning by remember { mutableStateOf(false) }
    LaunchedEffect(timerRunning) {
        while (timerRunning) {
            time++
            delay(1000) // 1 second delay
        }
    }

    val hours = time / 3600
    val minutes = (time % 3600) / 60
    val seconds = time % 60

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Time: ${formatTime(hours)}:${formatTime(minutes)}:${formatTime(seconds)}", // formats it to hh:mm:ss
                modifier = Modifier.padding(bottom = 32.dp),
                fontSize = 40.sp
            )
            Row(
                modifier = Modifier.padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { timerRunning = !timerRunning },
                    modifier = Modifier.size(100.dp, 50.dp) // makes button bigger
                        .padding(end = 8.dp) // adds spacing b/w buttons

                ) {
                    Text(if (timerRunning) "Stop" else "Start")
                }
                if (timerRunning.not()) {
                    Button(
                        onClick = { time = 0 },
                        modifier = Modifier.size(100.dp, 50.dp) // makes button bigger
                            .padding(start = 8.dp) // adds spacing b/w buttons
                    ) {
                        Text("Reset")
                    }
                }
            }
        }
    }
}

private fun formatTime(time: Int): String {
    return if (time < 10) "0$time" else "$time" // formatting the time
}

@Preview(showBackground = true)
@Composable
fun StopWatchAppPreview() {
    StopWatchTheme {
        StopWatchApp()
        }
}