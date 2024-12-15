package ru.istu.smartdevices.bluetooth

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.istu.smartdevices.bluetooth.service.BluetoothServiceInterface
import ru.istu.smartdevices.bluetooth.service.MockBluetoothService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindBluetoothService(mockBluetoothService: MockBluetoothService): BluetoothServiceInterface
}
