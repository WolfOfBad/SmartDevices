package ru.istu.smartdevices.bluetooth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BluetoothViewModel
    @Inject
    constructor(
        private val bluetoothService: BluetoothService,
    ) : ViewModel() {
        val connectionState = bluetoothService.connectionState
        val metrics = bluetoothService.metrics

        fun updateMetrics() {
            viewModelScope.launch {
                bluetoothService.fetchMetrics()
            }
        }
    }