pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        maven("https://repository.map.naver.com/archive/maven")
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven("https://repository.map.naver.com/archive/maven")
        maven("https://jitpack.io")
        mavenCentral()
    }
}

rootProject.name = "fitmily_android"
include(":app")
include(":fitmily_wearos")
