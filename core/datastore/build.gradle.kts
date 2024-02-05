plugins {
    alias(libs.plugins.nari.android.library)
    alias(libs.plugins.nari.android.library.jacoco)
    alias(libs.plugins.nari.android.hilt)
}

android {
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }
    namespace = "com.welldressedmen.nari.core.datastore"
    testOptions {
        unitTests {
            // 테스트 중에 안드로이드 프레임워크 메서드 호출이 실패하거나 null을 반환하는 대신 기본값을 반환하도록 설정
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    api(libs.androidx.dataStore.core)
    api(projects.core.datastoreProto)
    api(projects.core.model)

    implementation(projects.core.common)

    testImplementation(projects.core.datastoreTest)
    testImplementation(libs.kotlinx.coroutines.test)
}
