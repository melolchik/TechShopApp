plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.kapt)
    alias(libs.plugins.dagger.hilt.android)
}

android {
    namespace = "ru.melolchik.techshopapp"

    compileSdk = 36
//    compileSdk {
//        version = release(36)
//    }

    defaultConfig {
        applicationId = "ru.melolchik.techshopapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

}

dependencies {

    implementation(project(":core:database"))
    implementation(project(":core:network"))

    implementation(project(":domain:products"))
    implementation(project(":data:products"))

    implementation(project(":domain:datastore"))
    implementation(project(":data:datastore"))

    implementation(project(":core:ui"))
    implementation(project(":feature:splash"))
    implementation(project(":feature:products"))
    implementation(project(":feature:favorites"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:details"))

    // Hilt
    implementation(libs.hilt)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.appcompat)
    kapt(libs.hilt.dagger.compiler)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.androidx.compiler)

    //WorkManager
    implementation(libs.work.runtime.ktx)
    implementation(libs.hilt.work)

    //Gson
    implementation(libs.google.gson)

    // Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}