
package ru.istu.smartdevices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.wear.ambient.AmbientModeSupport
import androidx.wear.compose.material.MaterialTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity(), AmbientModeSupport.AmbientCallbackProvider {
    private var isConnected = true // Заглушка для статуса подключения

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MetricsScreen(
                    steps = "0000",
                    pulse = "00",
                    distance = "0.0",
                    sleep = "0ч 0м",
                    connectionStatus = if (isConnected) "Подключено" else "Отключено",
                )
            }
        }

        // Обновление данных каждые 5 секунд
        lifecycleScope.launch {
            while (true) {
                delay(5000)
                updateMetrics()
            }
        }
    }

    private fun updateMetrics() {
        // Заглушка для обновления данных
        // В реальном приложении здесь может быть запрос к датчикам или базе данных
    }

    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback {
        return object : AmbientModeSupport.AmbientCallback() {}
    }
}
