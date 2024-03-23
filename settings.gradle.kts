@file:Suppress("UnstableApiUsage")

import org.gradle.api.initialization.resolve.RepositoriesMode.FAIL_ON_PROJECT_REPOS

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
 //  repositoriesMode.set(FAIL_ON_PROJECT_REPOS)
    this.repositories {
          google()
        mavenCentral()
    }
}

rootProject.name = "Sorteiosdamega"
include(":app")
 