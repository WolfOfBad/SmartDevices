
package com.example.wearosmetrics.bluetooth

import kotlinx.coroutines.flow.StateFlow
import ru.istu.smartdevices.bluetooth.BluetoothService

interface BluetoothServiceInterface {
    val connectionState: StateFlow<Boolean>
    val metrics: StateFlow<BluetoothService.Metrics>
    fun fetchMetrics()
    fun connect()
    fun disconnect()
}
