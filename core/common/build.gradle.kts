plugins {
    alias(libs.plugins.nari.android.library)
    alias(libs.plugins.nari.android.library.jacoco)
    alias(libs.plugins.nari.android.hilt)
}

android {
    namespace = "com.welldressedmen.nari.core.common"
}

dependencies {
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}