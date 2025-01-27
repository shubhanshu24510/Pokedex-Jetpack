plugins {
    id("shubhans.pokedex.android.library")
    id("shubhans.pokedex.android.library.compose")
    id("shubhans.pokedex.spotless")
}

android {
    namespace = "com.shubhans.core.presentation.designsystem"
}

dependencies {
    // image loading
    api(libs.landscapist.glide)
    api(libs.landscapist.animation)
    api(libs.landscapist.placeholder)
    api(libs.landscapist.palette)

    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.animation)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
}