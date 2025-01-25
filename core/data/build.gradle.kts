plugins {
    id("shubhans.pokedex.android.library")
    id("shubhans.pokedex.android.hilt")
    id("shubhans.pokedex.spotless")
}

android {
    namespace = "com.shubhans.core.data"
}

dependencies {
    // core modules
    api(projects.core.model)
    implementation(projects.core.network)
    implementation(projects.core.database)

    // kotlinx
    api(libs.kotlinx.immutable.collection)

    // coroutines
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)

    // network
    implementation(libs.sandwich)
}