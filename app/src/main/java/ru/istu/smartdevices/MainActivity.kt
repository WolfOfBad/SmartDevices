package ru.istu.smartdevices

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.wear.ambient.AmbientModeSupport
import androidx.wear.compose.material.MaterialTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.istu.smartdevices.bluetooth.BluetoothViewModel

@AndroidEntryPoint
class MainActivity :
    ComponentActivity(),
    AmbientModeSupport.AmbientCallbackProvider {
    private lateinit var viewModel: BluetoothViewModel
    private lateinit var notificationManager: NotificationManager
    private val showHighPulseWarning = mutableStateOf(false)

    companion object {
        const val CHANNEL_ID = "high_pulse_channel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[BluetoothViewModel::class.java]
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel()

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
                    showHighPulseWarning = showHighPulseWarning.value,
                )
            }
        }

        lifecycleScope.launch {
            while (true) {
                delay(1000)
                viewModel.updateMetrics()
                val pulse =
                    viewModel.metrics.value.pulse
                        .toIntOrNull() ?: 0
                showHighPulseWarning.value = pulse > 99
            }
        }
    }

    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback = object : AmbientModeSupport.AmbientCallback() {}

    private fun createNotificationChannel() {
        val name = "High Pulse Notification"
        val descriptionText = "Notification for high pulse"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel =
            NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
        notificationManager.createNotificationChannel(channel)
    }
}
