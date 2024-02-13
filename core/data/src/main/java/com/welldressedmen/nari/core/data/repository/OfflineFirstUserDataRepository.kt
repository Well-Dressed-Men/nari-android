package com.welldressedmen.nari.core.data.repository

import com.welldressedmen.nari.core.datastore.NariPreferencesDataSource
import com.welldressedmen.nari.core.model.data.DarkThemeConfig
import com.welldressedmen.nari.core.model.data.Gender
import com.welldressedmen.nari.core.model.data.SurveyAnswer
import com.welldressedmen.nari.core.model.data.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFirstUserDataRepository @Inject constructor(
    private val nariPreferencesDataSource: NariPreferencesDataSource,
): UserDataRepository {

    override val userData: Flow<UserData> = nariPreferencesDataSource.userData
    override suspend fun setIsLogin(isLogin: Boolean) {
        nariPreferencesDataSource.setIsLogin(isLogin)
    }

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        nariPreferencesDataSource.setDarkThemeConfig(darkThemeConfig)
    }

    override suspend fun setGender(gender: Gender) {
        nariPreferencesDataSource.setGender(gender)
    }

    override suspend fun setSurveyCold(surveyCold: SurveyAnswer) {
        nariPreferencesDataSource.setSurveyCold(surveyCold)
    }

    override suspend fun setSurveyHot(surveyHot: SurveyAnswer) {
        nariPreferencesDataSource.setSurveyHot(surveyHot)
    }

    override suspend fun setHeight(height: Float) {
        nariPreferencesDataSource.setHeight(height)
    }

    override suspend fun setWeight(weight: Float) {
        nariPreferencesDataSource.setWeight(weight)
    }

    override suspend fun setFashionPreferences(fashionPreferences: Set<String>) {
        nariPreferencesDataSource.setFashionPreferences(fashionPreferences)
    }
}