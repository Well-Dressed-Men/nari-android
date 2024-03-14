package com.welldressedmen.nari.core.data.di

import com.welldressedmen.nari.core.data.repository.OfflineFirstUserDataRepository
import com.welldressedmen.nari.core.data.repository.UserDataRepository
import com.welldressedmen.nari.core.data.util.ConnectivityManagerNetworkMonitor
import com.welldressedmen.nari.core.data.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsUserDataRepository(
        userDataRepository: OfflineFirstUserDataRepository,
    ): UserDataRepository

    @Binds
    internal abstract fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor
}