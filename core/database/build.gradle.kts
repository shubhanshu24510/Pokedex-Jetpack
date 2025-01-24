plugins {
    id("shubhans.pokedex.android.library")
    id("shubhans.pokedex.android.hilt")
    id("shubhans.pokedex.spotless")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.shubhans.core.database"

    defaultConfig {
        // The schemas directory contains a schema file for each version of the Room database.
        // This is required to enable Room auto migrations.
        // See https://developer.android.com/reference/kotlin/androidx/room/AutoMigration.
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
    sourceSets.getByName("test") {
        assets.srcDir(files("$projectDir/schemas"))
    }
}

dependencies {
    implementation(projects.core.domain)

    // coroutines
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)

    // database
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    testImplementation(libs.androidx.arch.core.testing)

    // json parsing
    implementation(libs.kotlinx.serialization.json)
}