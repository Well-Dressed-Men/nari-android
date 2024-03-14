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

//    implementation(libs.google.oss.licenses)
//    implementation(libs.google.oss.licenses.plugin)
    implementation(libs.play.services.auth)

//    testImplementation(projects.core.testing)

//    androidTestImplementation(projects.core.testing)
}
