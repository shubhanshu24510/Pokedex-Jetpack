plugins {
    id("shubhans.pokedex.android.library")
    id("shubhans.pokedex.spotless")
}

android {
    namespace = "com.shubhans.core.presentation.viewmodel"
}

dependencies {
    api(libs.androidx.lifecycle.viewModelCompose)
}