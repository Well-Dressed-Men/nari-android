package com.welldressedmen.nari.core.data.repository

import com.welldressedmen.nari.core.datastore.NariPreferencesDataSource
import com.welldressedmen.nari.core.datastore.test.testUserPreferencesDataStore
import com.welldressedmen.nari.core.model.data.DarkThemeConfig
import com.welldressedmen.nari.core.model.data.Gender
import com.welldressedmen.nari.core.model.data.SurveyAnswer
import com.welldressedmen.nari.core.model.data.UserData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class OfflineFirstUserDataRepositoryTest {

    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var subject: OfflineFirstUserDataRepository

    private lateinit var nariPreferencesDataSource: NariPreferencesDataSource

    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @Before
    fun setup() {
        nariPreferencesDataSource = NariPreferencesDataSource(
            tmpFolder.testUserPreferencesDataStore(testScope),
        )

        subject = OfflineFirstUserDataRepository(
            nariPreferencesDataSource = nariPreferencesDataSource,
        )
    }

    @Test
    fun offlineFirstUserDataRepository_default_user_data_is_correct() {
        testScope.runTest {
            assertEquals(
                UserData(
                    isLogin = false,
                    darkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
                    gender = Gender.UNSPECIFIED,
                    surveyCold = SurveyAnswer.NO_REPLY,
                    surveyHot = SurveyAnswer.NO_REPLY,
                    height = 0.0f,
                    weight = 0.0f,
                    fashionPreferences = emptySet()
                ),
                subject.userData.first()
            )
        }
    }

    @Test
    fun offlineFirstUserDataRepository_set_is_login() {
        testScope.runTest {
            subject.setIsLogin(true)
            assertTrue(subject.userData.first().isLogin)

            subject.setIsLogin(false)
            assertFalse(subject.userData.first().isLogin)
        }
    }

    @Test
    fun offlineFirstUserDataRepository_set_dark_theme_config() {
        testScope.runTest {
            subject.setDarkThemeConfig(DarkThemeConfig.LIGHT)
            assertEquals(DarkThemeConfig.LIGHT, subject.userData.map { it.darkThemeConfig }.first())
            assertEquals(DarkThemeConfig.LIGHT, nariPreferencesDataSource.userData.map { it.darkThemeConfig }.first())

            subject.setDarkThemeConfig(DarkThemeConfig.DARK)
            assertEquals(DarkThemeConfig.DARK, subject.userData.first().darkThemeConfig)

            subject.setDarkThemeConfig(DarkThemeConfig.FOLLOW_SYSTEM)
            assertEquals(DarkThemeConfig.FOLLOW_SYSTEM, subject.userData.first().darkThemeConfig)
        }
    }

    @Test
    fun offlineFirstUserDataRepository_set_fashion_preferences() {
        testScope.runTest {
            subject.setFashionPreferences(fashionPreferences = setOf("Casual", "Modern"))

            assertEquals(
                setOf("Casual", "Modern"),
                subject.userData.map { it.fashionPreferences }.first()
            )

            assertEquals(
                nariPreferencesDataSource.userData.map { it.fashionPreferences }.first(),
                subject.userData.map { it.fashionPreferences }.first()
            )
        }
    }
}