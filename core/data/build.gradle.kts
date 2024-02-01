plugins {
    alias(libs.plugins.nari.android.library)
    alias(libs.plugins.nari.android.library.jacoco)
    alias(libs.plugins.nari.android.hilt)
}

android {
    namespace = "com.welldressedmen.nari.core.data"

}

dependencies {
    api(projects.core.common)

    testImplementation(libs.kotlinx.coroutines.test)
}