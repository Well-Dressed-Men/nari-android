plugins {
    alias(libs.plugins.nari.android.library)
    alias(libs.plugins.nari.android.library.jacoco)
    alias(libs.plugins.nari.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.welldressedmen.nari.core.data"

}

dependencies {
    api(projects.core.common)
    api(projects.core.datastore)

    testImplementation(projects.core.datastoreTest)
    testImplementation(libs.kotlinx.coroutines.test)
}