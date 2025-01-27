plugins {
    id("shubhans.pokedex.android.library")
    id("shubhans.pokedex.android.library.compose")
    alias(libs.plugins.kotlinx.serialization)
    id("shubhans.pokedex.android.hilt")
    id("shubhans.pokedex.spotless")
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