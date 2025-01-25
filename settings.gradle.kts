pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Pokedex"
include(":app")
include(":core:data")
include(":core:model")
include(":core:database")
include(":feature:home")
include(":feature:details")
include(":core:network")
include(":core:previews")
include(":core:presentation:designsystem")
include(":core:presentation:navigationUi")
