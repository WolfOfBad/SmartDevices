package ru.istu.smartdevices.bluetooth

import android.bluetooth.BluetoothDevice
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import no.nordicsemi.android.ble.BleManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BluetoothService
    @Inject
    constructor(
        @ApplicationContext
        private val context: Context,
    ) : BleManager(context) {
        private val _connectionState = MutableStateFlow(false)
        val connectionState: StateFlow<Boolean> get() = _connectionState

        private val _metrics = MutableStateFlow(Metrics())
        val metrics: StateFlow<Metrics> get() = _metrics

        fun onDeviceConnected(device: BluetoothDevice) {
            _connectionState.value = true
        }

        fun onDeviceDisconnected() {
            _connectionState.value = false
        }


        fun fetchMetrics() {
            _metrics.value =
                Metrics(
                    steps = "1234",
                    pulse = "76",
                    distance = "1.2",
                    sleep = "6ч 45м",
                )
        }

        data class Metrics(
            val steps: String = "0",
            val pulse: String = "0",
            val distance: String = "0.0",
            val sleep: String = "0ч 0м",
        )
    }
