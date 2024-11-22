package com.example.wearosmetrics.bluetooth

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
                    pulse = "60",
                    distance = "0.0",
                    sleep = "0ч 0м",
                ),
            )
        override val metrics: StateFlow<BluetoothServiceInterface.Metrics> get() = _metrics

        private val _connectionState = MutableStateFlow(true)
        override val connectionState: StateFlow<Boolean> get() = _connectionState

        private fun getHeartRate(oldHeartRate: Int): String {
            var pulse = (metrics.value.pulse.toInt() + (-5..5).random())
            if (pulse < 60) {
                return "60"
            }
            if (pulse > 120) {
                return "120"
            }
            return pulse.toString()
        }

        override fun fetchMetrics() {
            // Симуляция обновления данных каждые 5 секунд
            _metrics.value =
                BluetoothServiceInterface.Metrics(
                    steps = (metrics.value.steps.toInt() + (1..5).random()).toString(),
                    pulse = getHeartRate(metrics.value.pulse.toInt()),
                    distance = String.format("%.2f", metrics.value.distance.toDouble() + 0.01),
                    sleep = "7ч 30м",
                )
        }

        override fun connect() {
            _connectionState.value = true
        }

        override fun disconnect() {
            _connectionState.value = false
        }
    }
