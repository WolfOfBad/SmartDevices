package ru.istu.smartdevices.bluetooth.service

import kotlinx.coroutines.flow.StateFlow

interface BluetoothServiceInterface {
    data class Metrics(
        val steps: String,
        val pulse: String,
        val distance: String,
        val sleep: String,
    )

    val metrics: StateFlow<Metrics>
    val connectionState: StateFlow<Boolean>

    fun fetchMetrics()

    fun connect()

    fun disconnect()
}
