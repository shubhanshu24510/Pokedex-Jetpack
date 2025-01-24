plugins {
  `kotlin-dsl`
}

group = "com.skydoves.pokedex.compose.buildlogic"

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(18))
  }
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.compose.compiler.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
  compileOnly(libs.spotless.gradlePlugin)
}

gradlePlugin {
  plugins {
    register("androidApplicationCompose") {
      id = "shubhans.pokedex.android.application.compose"
      implementationClass = "AndroidApplicationComposeConventionPlugin"
    }
    register("androidApplication") {
      id = "shubhans.pokedex.android.application"
      implementationClass = "AndroidApplicationConventionPlugin"
    }
    register("androidLibraryCompose") {
      id = "shubhans.pokedex.android.library.compose"
      implementationClass = "AndroidLibraryComposeConventionPlugin"
    }
    register("androidLibrary") {
      id = "shubhans.pokedex.android.library"
      implementationClass = "AndroidLibraryConventionPlugin"
    }
    register("androidFeature") {
      id = "shubhans.pokedex.android.feature"
      implementationClass = "AndroidFeatureConventionPlugin"
    }
    register("androidHilt") {
      id = "shubhans.pokedex.android.hilt"
      implementationClass = "AndroidHiltConventionPlugin"
    }
    register("spotless") {
      id = "shubhans.pokedex.spotless"
      implementationClass = "SpotlessConventionPlugin"
    }
  }
}
