plugins {
    id("shubhans.pokedex.android.library")
    id("shubhans.pokedex.android.library.compose")
    id("shubhans.pokedex.android.hilt")
    id("shubhans.pokedex.spotless")
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.shubhans.core.presentation.navigation"
}

dependencies {
    implementation(projects.core.model)

    implementation(libs.androidx.core)
    implementation(libs.kotlinx.coroutines.android)

    api(libs.androidx.navigation.compose)

    // json parsing
    implementation(libs.kotlinx.serialization.json)
}