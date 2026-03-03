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

rootProject.name = "TechShopApp"
include(":app")
include(":core:network")
include(":core:database")
include(":core:datastore")
include(":core:ui")
include(":feature:products")
include(":feature:favorites")
include(":feature:details")
include(":domain:products")
include(":data:products")
include(":feature:splash")
