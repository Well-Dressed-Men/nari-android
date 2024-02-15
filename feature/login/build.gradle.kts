plugins {
    alias(libs.plugins.nari.android.feature)
    alias(libs.plugins.nari.android.library.compose)
    alias(libs.plugins.nari.android.library.jacoco)
}

android {
    namespace = "com.welldressedmen.nari.feature.login"
}

dependencies {
    implementation(projects.core.data)
//    implementation(projects.core.domain)


}