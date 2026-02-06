plugins {
    alias(libs.plugins.store5practice.android.library)
    alias(libs.plugins.store5practice.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.store5practice.core.network"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    api(libs.kotlinx.datetime)
    api(libs.kotlinx.serialization.json)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}
