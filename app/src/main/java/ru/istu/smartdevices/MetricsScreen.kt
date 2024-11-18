package ru.istu.smartdevices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Suppress("ktlint:standard:function-naming")
fun MetricsScreen(
    steps: String,
    pulse: String,
    distance: String,
    sleep: String,
    connectionStatus: String,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = connectionStatus,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp),
        )
        Text(text = "Кол-во шагов: $steps", fontSize = 16.sp)
        Text(text = "Пульс: $pulse", fontSize = 16.sp)
        Text(text = "Дистанция: $distance", fontSize = 16.sp)
        Text(text = "Сон: $sleep", fontSize = 16.sp)
    }
}
