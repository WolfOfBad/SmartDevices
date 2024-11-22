
package com.example.wearosmetrics.bluetooth

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.istu.smartdevices.bluetooth.BluetoothService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockBluetoothService @Inject constructor() : BluetoothServiceInterface {

    private val _connectionState = MutableStateFlow(false)
    override val connectionState: StateFlow<Boolean> get() = _connectionState

    private val _metrics = MutableStateFlow(BluetoothService.Metrics())
    override val metrics: StateFlow<BluetoothService.Metrics> get() = _metrics

    override fun fetchMetrics() {
        // Симуляция обновления данных
        _metrics.value = BluetoothService.Metrics(
            steps = (1000..5000).random().toString(),
            pulse = (60..100).random().toString(),
            distance = (1..10).random().toString() + "." + (0..9).random(),
            sleep = "${(4..8).random()}ч ${(0..59).random()}м"
        )
    }

    override fun connect() {
        // Симуляция подключения
        _connectionState.value = true
    }

    override fun disconnect() {
        // Симуляция отключения
        _connectionState.value = false
    }
}
