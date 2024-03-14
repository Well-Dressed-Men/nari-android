plugins {
    alias(libs.plugins.nari.android.library)
    alias(libs.plugins.nari.android.library.compose)
    alias(libs.plugins.nari.android.library.jacoco)
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "com.welldressedmen.nari.core.ui"
}

dependencies {
    api(libs.androidx.metrics)
    api(projects.core.designsystem)
    api(projects.core.model)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
}