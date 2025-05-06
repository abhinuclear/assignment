pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS) // This disables repositories in build.gradle.kts
    repositories {
        google()
        mavenCentral()
    }
}
include(":app")