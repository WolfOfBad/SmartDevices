package com.example.wearosmetrics.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Suppress("ktlint:standard:function-naming")
@Composable
fun MetricsScreen(
    steps: String,
    pulse: String,
    distance: String,
    sleep: String,
    connectionStatus: String,
    showHighPulseWarning: Boolean
) {
    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Шаги: $steps")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Пульс: $pulse")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Дистанция: $distance км")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Сон: $sleep")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Статус подключения: $connectionStatus")

        if (showHighPulseWarning) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Внимание: Ваш пульс слишком высок!", color = androidx.compose.ui.graphics.Color.Red)
        }
    }
}