package com.example.wearosmetrics.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MetricsScreen(
    steps: String,
    pulse: String,
    distance: String,
    sleep: String,
    connectionStatus: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
    }
}