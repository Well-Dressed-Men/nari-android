package com.welldressedmen.nari.core.data.repository

import com.welldressedmen.nari.core.model.data.DarkThemeConfig
import com.welldressedmen.nari.core.model.data.Gender
import com.welldressedmen.nari.core.model.data.SurveyAnswer
import com.welldressedmen.nari.core.model.data.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    val userData: Flow<UserData>

    suspend fun setIsLogin(isLogin: Boolean)

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)

    suspend fun setGender(gender: Gender)

    suspend fun setSurveyCold(surveyCold: SurveyAnswer)

    suspend fun setSurveyHot(surveyHot: SurveyAnswer)

    suspend fun setHeight(height: Float)

    suspend fun setWeight(weight: Float)

    suspend fun setFashionPreferences(fashionPreferences: Set<String>)
}