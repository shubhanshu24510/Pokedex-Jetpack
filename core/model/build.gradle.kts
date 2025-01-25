plugins {
    id("shubhans.pokedex.android.library")
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
    id("shubhans.pokedex.spotless")
}

android {
    namespace = "com.shubhans.domain"
}

dependencies {
    // compose stable marker
    compileOnly(libs.compose.stable.marker)

    // Kotlin Serialization for Json
    implementation(libs.kotlinx.serialization.json)

    // kotlinx
    api(libs.kotlinx.immutable.collection)
}