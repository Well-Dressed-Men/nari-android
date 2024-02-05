package com.welldressedmen.nari.core.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import com.welldressedmen.nari.core.model.data.DarkThemeConfig
import com.welldressedmen.nari.core.model.data.Gender
import com.welldressedmen.nari.core.model.data.SurveyAnswer
import com.welldressedmen.nari.core.model.data.UserData
import kotlinx.coroutines.flow.map
import java.io.IOException
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
                },
                gender = when (it.gender) {
                    null,
                    GenderProto.UNRECOGNIZED,
                    GenderProto.GENDER_UNSPECIFIED,
                    -> Gender.UNSPECIFIED

                    GenderProto.GENDER_MALE,
                    -> Gender.MALE

                    GenderProto.GENDER_FEMALE,
                    -> Gender.FEMALE
                },
                surveyCold = fromSurveyAnswerProtoToSurveyAnswer(it.surveyAnswerCold),
                surveyHot = fromSurveyAnswerProtoToSurveyAnswer(it.surveyAnswerHot),
                height = it.height,
                weight = it.weight,
                fashionPreferences = it.fashionStylePreferencesMap.keys
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

    suspend fun setGender(gender: Gender) {
        userPreference.updateData {
            it.copy {
                this.gender = when (gender) {
                    Gender.UNSPECIFIED -> GenderProto.GENDER_UNSPECIFIED
                    Gender.MALE -> GenderProto.GENDER_MALE
                    Gender.FEMALE -> GenderProto.GENDER_FEMALE
                }
            }
        }
    }

    suspend fun setSurveyCold(surveyAnswer: SurveyAnswer) {
        userPreference.updateData {
            it.copy {
                this.surveyAnswerCold = when (surveyAnswer) {
                    SurveyAnswer.VERY_NOT -> SurveyAnswerProto.SURVEY_ANSWER_VERY_NOT
                    SurveyAnswer.NOT -> SurveyAnswerProto.SURVEY_ANSWER_NOT
                    SurveyAnswer.NEUTRAL -> SurveyAnswerProto.SURVEY_ANSWER_NEUTRAL
                    SurveyAnswer.YES -> SurveyAnswerProto.SURVEY_ANSWER_YES
                    SurveyAnswer.VERY_YES -> SurveyAnswerProto.SURVEY_ANSWER_VERY_YES
                }
            }
        }
    }

    suspend fun setSurveyHot(surveyAnswer: SurveyAnswer) {
        userPreference.updateData {
            it.copy {
                this.surveyAnswerHot = when (surveyAnswer) {
                    SurveyAnswer.VERY_NOT -> SurveyAnswerProto.SURVEY_ANSWER_VERY_NOT
                    SurveyAnswer.NOT -> SurveyAnswerProto.SURVEY_ANSWER_NOT
                    SurveyAnswer.NEUTRAL -> SurveyAnswerProto.SURVEY_ANSWER_NEUTRAL
                    SurveyAnswer.YES -> SurveyAnswerProto.SURVEY_ANSWER_YES
                    SurveyAnswer.VERY_YES -> SurveyAnswerProto.SURVEY_ANSWER_VERY_YES
                }
            }
        }
    }

    suspend fun setHeight(height: Float) {
        userPreference.updateData {
            it.copy { this.height = height }
        }
    }

    suspend fun setWeight(weight: Float) {
        userPreference.updateData {
            it.copy { this.weight = weight }
        }
    }

    suspend fun setFashionPreferences(fashionPreferences: Set<String>) {
        try {
            userPreference.updateData {
                it.copy {
                    fashionStylePreferences.clear()
                    fashionStylePreferences.putAll(fashionPreferences.associateWith { true })
                }
            }
        } catch (ioException: IOException) {
            Log.e("NariPreferences", "Failed to update user preferences", ioException)
        }
    }

    fun fromSurveyAnswerProtoToSurveyAnswer(proto: SurveyAnswerProto): SurveyAnswer =
        when (proto) {
            SurveyAnswerProto.SURVEY_ANSWER_VERY_NOT -> SurveyAnswer.VERY_NOT
            SurveyAnswerProto.SURVEY_ANSWER_NOT -> SurveyAnswer.NOT
            SurveyAnswerProto.UNRECOGNIZED,
            SurveyAnswerProto.SURVEY_ANSWER_NEUTRAL -> SurveyAnswer.NEUTRAL
            SurveyAnswerProto.SURVEY_ANSWER_YES -> SurveyAnswer.YES
            SurveyAnswerProto.SURVEY_ANSWER_VERY_YES -> SurveyAnswer.VERY_YES
        }
}