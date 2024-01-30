package com.welldressedmen.nari.core.data.repository

import com.welldressedmen.nari.core.model.data.DarkThemeConfig
import com.welldressedmen.nari.core.model.data.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    /**
     * Stream of [UserData]
     */
    val userData: Flow<UserData>

    /**
     * Sets whether the user has completed the login process.
     */
    suspend fun setIsLogin(isLogin: Boolean)

    /**
     * Sets the desired dark theme config.
     */
    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)

}
