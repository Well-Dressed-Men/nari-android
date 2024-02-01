package com.welldressedmen.nari.core.datastore

import androidx.datastore.core.DataStore
import com.welldressedmen.nari.core.model.DarkThemeConfig
import com.welldressedmen.nari.core.model.UserData
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NariPreferencesDataSource @Inject constructor(
    private val userPreference: DataStore<UserPreferences>,
) {
    val userData = userPreference.data
        .map {
            UserData(
                isLogin = it.isLogin,
                darkThemeConfig = when (it.darkThemeConfig) {
                    null,
                    DarkThemeConfigProto.DARK_THEME_CONFIG_UNSPECIFIED,
                    DarkThemeConfigProto.UNRECOGNIZED,
                    DarkThemeConfigProto.DARK_THEME_CONFIG_FOLLOW_SYSTEM,
                    -> DarkThemeConfig.FOLLOW_SYSTEM

                    DarkThemeConfigProto.DARK_THEME_CONFIG_LIGHT,
                    -> DarkThemeConfig.LIGHT

                    DarkThemeConfigProto.DARK_THEME_CONFIG_DARK,
                    -> DarkThemeConfig.DARK
                }
            )
        }

    suspend fun setIsLogin(isLogin: Boolean) {
        userPreference.updateData {
            it.copy {
                this.isLogin = isLogin
            }
        }
    }
    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        userPreference.updateData {
            it.copy {
                this.darkThemeConfig = when (darkThemeConfig) {
                    DarkThemeConfig.FOLLOW_SYSTEM -> DarkThemeConfigProto.DARK_THEME_CONFIG_FOLLOW_SYSTEM
                    DarkThemeConfig.LIGHT -> DarkThemeConfigProto.DARK_THEME_CONFIG_LIGHT
                    DarkThemeConfig.DARK -> DarkThemeConfigProto.DARK_THEME_CONFIG_DARK
                }
            }
        }
    }
}