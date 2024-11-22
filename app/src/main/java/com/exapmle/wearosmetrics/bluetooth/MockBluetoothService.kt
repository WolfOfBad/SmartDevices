package com.example.wearosmetrics.bluetooth

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockBluetoothService @Inject constructor() : BluetoothServiceInterface {

    private val _metrics = MutableStateFlow(
        BluetoothServiceInterface.Metrics(
            steps = "1234",
            pulse = "80",
            distance = "2.5",
            sleep = "6ч 45м"
        )
    )
    override val metrics: StateFlow<BluetoothServiceInterface.Metrics> get() = _metrics

    private val _connectionState = MutableStateFlow(true)
    override val connectionState: StateFlow<Boolean> get() = _connectionState

    override fun fetchMetrics() {
        // Симуляция обновления данных каждые 5 секунд
        _metrics.value = BluetoothServiceInterface.Metrics(
            steps = (1000..5000).random().toString(),
            pulse = (60..120).random().toString(),
            distance = "${(1..10).random()}.${(0..9).random()}",
            sleep = "${(4..8).random()}ч ${(0..59).random()}м"
        )
    }

    override fun connect() {
        _connectionState.value = true
    }

    override fun disconnect() {
        _connectionState.value = false
    }
}