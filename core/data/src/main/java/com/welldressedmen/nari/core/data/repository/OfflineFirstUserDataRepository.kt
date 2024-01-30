package com.welldressedmen.nari.core.data.repository

import com.welldressedmen.nari.core.datastore.NariPreferencesDataSource
import com.welldressedmen.nari.core.model.data.DarkThemeConfig
import com.welldressedmen.nari.core.model.data.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class OfflineFirstUserDataRepository @Inject constructor(
    private val nariPreferencesDataSource: NariPreferencesDataSource,
) : UserDataRepository {

    override val userData: Flow<UserData> = nariPreferencesDataSource.userData
    override suspend fun setIsLogin(isLogin: Boolean) {
        nariPreferencesDataSource.setIsLogin(isLogin)
    }

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        nariPreferencesDataSource.setDarkThemeConfig(darkThemeConfig)
    }


}