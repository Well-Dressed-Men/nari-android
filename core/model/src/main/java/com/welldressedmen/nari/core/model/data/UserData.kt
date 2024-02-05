package com.welldressedmen.nari.core.model.data

data class UserData(
    val isLogin: Boolean,
    val darkThemeConfig: DarkThemeConfig,
    val gender: Gender,
    val surveyCold: SurveyAnswer,
    val surveyHot: SurveyAnswer,
    val height: Float,
    val weight: Float,
    val fashionPreferences: Set<String>
)