plugins {
    id("shubhans.pokedex.android.library")
    id("shubhans.pokedex.android.library.compose")
    id("shubhans.pokedex.android.hilt")
    id("shubhans.pokedex.spotless")
}

android {
    namespace = "com.shubhans.core.preview"
}

dependencies {
    // core
    implementation(projects.core.designsystem)
    implementation(projects.core.navigation)
    implementation(projects.core.model)
}