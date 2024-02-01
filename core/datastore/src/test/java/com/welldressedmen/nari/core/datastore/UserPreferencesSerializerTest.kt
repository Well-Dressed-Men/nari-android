package com.welldressedmen.nari.core.datastore

import androidx.datastore.core.CorruptionException
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import kotlin.test.assertEquals

class UserPreferencesSerializerTest {
    private var userPreferencesSerializer = UserPreferencesSerializer()

    @Test
    fun defaultUserPreferences_IsEmpty() {
        assertEquals(
            userPreferences {

            },
            userPreferencesSerializer.defaultValue
        )
    }

    @Test
    fun writeAndReadUserPreferences_OutputsCorrectValue() = runTest {
        val expectedUserPreferences = userPreferences {
            isLogin = true
        }

        val outputStream = ByteArrayOutputStream()

        expectedUserPreferences.writeTo(outputStream)

        val inputStream = ByteArrayInputStream(outputStream.toByteArray())

        val actualUserPreferences = userPreferencesSerializer.readFrom(inputStream)

        assertEquals(
            expectedUserPreferences,
            actualUserPreferences
        )
    }

    @Test(expected = CorruptionException::class)
    fun readingInvalidUserPreferences_throwsCorruptionException() = runTest {
        userPreferencesSerializer.readFrom(ByteArrayInputStream(byteArrayOf(0)))
    }
}