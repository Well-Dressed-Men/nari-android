plugins {
    alias(libs.plugins.nari.android.library)
    alias(libs.plugins.nari.android.hilt)
}

android {
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }
    namespace = "com.welldressedmen.nari.core.datastore"
    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    api(libs.androidx.dataStore.core)
    api(projects.core.datastoreProto)
    api(projects.core.model)

    implementation(projects.core.common)

    testImplementation(libs.kotlinx.coroutines.test)
}
