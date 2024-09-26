plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt.android) // Add this line for Hilt

    // If using Kotlin, you may also need to apply the kapt plugin
    // alias(libs.plugins.kotlin.kapt) // Uncomment if using Kotlin
}

android {
    namespace = "com.prospect.myexpensive"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.prospect.myexpensive"
        minSdk = 22
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // Retrofit library
    implementation(libs.retrofit)
    // Retrofit with Gson converter (for JSON parsing)
    implementation(libs.retrofit.gson)
    // OkHttp logging interceptor (for debugging)
    implementation(libs.okhttp)
    // Hilt for dependency injection
    implementation(libs.hilt.android)
    implementation(libs.recyclerview)
    implementation(libs.legacy.support.v4)
    implementation(libs.annotation)

    // Use kapt if this is a Kotlin project
    // kapt(libs.hilt.compiler) // Uncomment if using Kotlin
    // Use annotationProcessor for Java projects
    annotationProcessor(libs.hilt.compiler) // This is correct for Java

    // ViewModel and LiveData
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}