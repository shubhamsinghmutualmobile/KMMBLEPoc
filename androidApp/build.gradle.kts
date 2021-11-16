plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.devtools.ksp") version "1.5.31-1.0.0"
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.activity:activity-ktx:1.4.0")
    implementation("androidx.fragment:fragment-ktx:1.3.6")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("dev.bluefalcon:blue-falcon:0.9.8")

    val composeVersion = "1.0.5"
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")

    implementation("io.github.raamcosta.compose-destinations:core:0.9.4-beta")
    ksp("io.github.raamcosta.compose-destinations:ksp:0.9.4-beta")

    // official compose navigation
    implementation("androidx.navigation:navigation-compose:2.4.0-beta02")

    val koinVersion = "3.1.3"
    implementation("io.insert-koin:koin-android:$koinVersion")
//    implementation("io.insert-koin:koin-android-viewmodel:$koinVersion")
    implementation("io.insert-koin:koin-androidx-compose:$koinVersion")
}

android {
    compileSdk = 31
    sourceSets["debug"].java.srcDir(file("build/generated/ksp/debug/kotlin"))
    defaultConfig {
        applicationId = "com.mutualmobile.kmmblepoc.android"
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.5"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
