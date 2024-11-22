package ru.istu.smartdevices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.wear.ambient.AmbientModeSupport
import androidx.wear.compose.material.MaterialTheme
import com.example.wearosmetrics.ui.MetricsScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.istu.smartdevices.bluetooth.BluetoothViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity(), AmbientModeSupport.AmbientCallbackProvider {
    private lateinit var viewModel: BluetoothViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[BluetoothViewModel::class.java]

        setContent {
            MaterialTheme {
                val metricsState = viewModel.metrics.collectAsState()
                val connectionState = viewModel.connectionState.collectAsState()

                val metrics = metricsState.value
                val isConnected = connectionState.value

                MetricsScreen(
                    steps = metrics.steps,
                    pulse = metrics.pulse,
                    distance = metrics.distance,
                    sleep = metrics.sleep,
                    connectionStatus = if (isConnected) "Подключено" else "Отключено",
                )
            }
        }

        // Обновление данных каждые 5 секунд
        lifecycleScope.launch {
            while (true) {
                delay(1000)
                viewModel.updateMetrics()
            }
        }
    }

    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback {
        return object : AmbientModeSupport.AmbientCallback() {}
    }
}
