plugins {
    id("shubhans.pokedex.android.library")
    id("shubhans.pokedex.android.hilt")
    id("shubhans.pokedex.spotless")
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.shubhans.core.network"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core.domain)

    // coroutines
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)

    // network
    implementation(libs.sandwich)
    implementation(platform(libs.retrofit.bom))
    implementation(platform(libs.okhttp.bom))
    implementation(libs.bundles.retrofitBundle)
    testImplementation(libs.okhttp.mockwebserver)
    testImplementation(libs.androidx.arch.core.testing)

    // json parsing
    implementation(libs.kotlinx.serialization.json)
}