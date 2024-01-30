plugins {
    alias(libs.plugins.nari.android.library)
    alias(libs.plugins.nari.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.welldressedmen.nari.core.data"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    api(projects.core.common)
//    api(projects.core.database)
    api(projects.core.datastore)
//    api(projects.core.network)

}
