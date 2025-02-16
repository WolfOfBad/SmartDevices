package ru.istu.smartdevices.bluetooth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.istu.smartdevices.bluetooth.service.BluetoothServiceInterface
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BluetoothViewModel
    @Inject
    constructor(
        private val bluetoothService: BluetoothServiceInterface,
    ) : ViewModel() {
        val connectionState = bluetoothService.connectionState
        val metrics = bluetoothService.metrics

        fun updateMetrics() {
            viewModelScope.launch {
                Timber.d("Updating metrics")
                bluetoothService.fetchMetrics()
                Timber.d("Metrics updated: ${metrics.value}")
            }
        }
    }
