package com.welldressedmen.nari.core.datastore

import com.welldressedmen.nari.core.datastore.test.testUserPreferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NariPreferencesDataSourceTest {

    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var nariPreferencesDataSource: NariPreferencesDataSource

    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @Before
    fun setup() {
        nariPreferencesDataSource = NariPreferencesDataSource(
            tmpFolder.testUserPreferencesDataStore(testScope),
        )
    }

    @Test
    fun isLoginIsFalseByDefault() = testScope.runTest {
        assertFalse(nariPreferencesDataSource.userData.first().isLogin)
    }

    @Test
    fun isLoginIsTrueWhenSet() = testScope.runTest {
        nariPreferencesDataSource.setIsLogin(true)
        assertTrue(nariPreferencesDataSource.userData.first().isLogin)
    }


}