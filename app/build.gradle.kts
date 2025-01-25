import java.util.Properties
import java.io.FileInputStream

plugins {
    id("shubhans.pokedex.android.application")
    id("shubhans.pokedex.android.application.compose")
    id("shubhans.pokedex.android.hilt")
    id("shubhans.pokedex.spotless")
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.baselineprofile)
}

android {
    namespace = "com.shubhans.pokedex"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.shubhans.pokedex"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        val properties = Properties()
        val localPropertyFile = project.rootProject.file("local.properties")
        if (localPropertyFile.canRead()) {
            properties.load(FileInputStream("$rootDir/local.properties"))
        }
        create("release") {
            storeFile = file(properties["RELEASE_KEYSTORE_PATH"] ?: "../keystores/pokedex.jks")
            keyAlias = properties["RELEASE_KEY_ALIAS"].toString()
            keyPassword = properties["RELEASE_KEY_PASSWORD"].toString()
            storePassword = properties["RELEASE_KEYSTORE_PASSWORD"].toString()
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles("proguard-rules.pro",)
            signingConfig = signingConfigs.getByName("release")

            kotlinOptions {
                freeCompilerArgs += listOf(
                    "-Xno-param-assertions",
                    "-Xno-call-assertions",
                    "-Xno-receiver-assertions"
                )
            }

            packaging {
                resources {
                    excludes += listOf(
                        "DebugProbesKt.bin",
                        "kotlin-tooling-metadata.json",
                        "kotlin/**",
                    )
                }
            }
        }
    }

    buildFeatures {
        buildConfig = true
    }

    hilt {
        enableAggregatingTask = true
    }

    kotlin {
        sourceSets.configureEach {
            kotlin.srcDir(layout.buildDirectory.files("generated/ksp/$name/kotlin/"))
        }
        sourceSets.all {
            languageSettings {
                languageVersion = "2.0"
            }
        }
    }

    testOptions.unitTests {
        isIncludeAndroidResources = true
        isReturnDefaultValues = true
    }
}


dependencies {
    // features
    implementation(projects.feature.home)
    implementation(projects.feature.details)

    // cores
    implementation(projects.core.model)
    implementation(projects.core.presentation.designsystem)
    implementation(projects.core.presentation.navigationUi)

    // compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.foundation)

    // di
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.testing)
    kspAndroidTest(libs.hilt.compiler)

    // unit test
    testImplementation(libs.junit)
    testImplementation(libs.turbine)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.truth)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
}