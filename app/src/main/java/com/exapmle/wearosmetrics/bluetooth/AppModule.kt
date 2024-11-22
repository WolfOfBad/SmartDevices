
package com.example.wearosmetrics.di

import com.example.wearosmetrics.bluetooth.BluetoothServiceInterface
import com.example.wearosmetrics.bluetooth.MockBluetoothService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindBluetoothService(
        mockBluetoothService: MockBluetoothService
    ): BluetoothServiceInterface
}
