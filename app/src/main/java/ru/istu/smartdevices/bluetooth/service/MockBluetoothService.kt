package ru.istu.smartdevices.bluetooth.service

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockBluetoothService
    @Inject
    constructor() : BluetoothServiceInterface {
        private val _metrics =
            MutableStateFlow(
                BluetoothServiceInterface.Metrics(
                    steps = "0000",
                    pulse = "100",
                    distance = "0.0",
                    sleep = "0ч 0м",
                ),
            )
        override val metrics: StateFlow<BluetoothServiceInterface.Metrics> get() = _metrics

        private val _connectionState = MutableStateFlow(true)
        override val connectionState: StateFlow<Boolean> get() = _connectionState

        private fun getHeartRate(oldHeartRate: Int): String {
            val pulse = (oldHeartRate + (-5..5).random())
            Timber.d("Calculated heart rate: $pulse")
            if (pulse < 60) {
                return "60"
            }
            if (pulse > 120) {
                return "120"
            }
            return pulse.toString()
        }

        override fun fetchMetrics() {
            Timber.d("Fetching metrics")
            _metrics.value =
                BluetoothServiceInterface.Metrics(
                    steps = (metrics.value.steps.toInt() + (1..5).random()).toString(),
                    pulse = getHeartRate(metrics.value.pulse.toInt()),
                    distance = String.format(Locale.US, "%.2f", metrics.value.distance.toDouble() + 0.01),
                    sleep = "7ч 30м",
                )
            Timber.d("Fetched metrics: ${_metrics.value}")
        }

        override fun connect() {
            _connectionState.value = true
            Timber.i("Bluetooth connected")
        }

        override fun disconnect() {
            _connectionState.value = false
            Timber.i("Bluetooth disconnected")
        }
    }
