
package ru.istu.smartdevices

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.Text

@Composable
@Suppress("ktlint:standard:function-naming")
fun MetricsScreen(
    steps: String,
    pulse: String,
    distance: String,
    sleep: String,
    connectionStatus: String,
) {
    Text("Steps: $steps, Pulse: $pulse, Distance: $distance, Sleep: $sleep, Connection: $connectionStatus")
}
