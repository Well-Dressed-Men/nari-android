plugins {
    alias(libs.plugins.nari.android.library)
    alias(libs.plugins.nari.android.hilt)
}

android {
    namespace = "com.welldressedmen.nari.core.datastore.test"
}

dependencies {
    implementation(libs.hilt.android.testing)
    implementation(projects.core.common)
    implementation(projects.core.datastore)
}
