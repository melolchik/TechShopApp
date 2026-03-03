// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.kapt) apply false

    alias(libs.plugins.dagger.hilt.android) apply false
}

subprojects {
    configurations.all {
        resolutionStrategy.eachDependency {
            if (requested.group == "org.jetbrains.kotlinx" && requested.name == "kotlinx-metadata-jvm") {
                useVersion("0.9.0") // 0.9.0 adds support for Kotlin 2.1 metadata
            }
        }
    }
}