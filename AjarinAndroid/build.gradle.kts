plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version Deps.kotlinVersion
}

android {
    namespace = "com.example.ajarin.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.example.ajarin.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.ajarin.TestHiltRunner"

        buildConfigField("String", "PREF_NAME", "\"AJARIN_PREFERENCE\"")
        buildConfigField("String", "ENCRYPTED_PREF_NAME", "\"TOKEN_PREFRENCE\"")
        buildConfigField("String", "USER_TOKEN_KEY", "\"USER_TOKEN\"")
        buildConfigField("String", "USER_REFRESH_TOKEN_KEY", "\"USER_REFRESH_TOKEN\"")
        buildConfigField("String", "SHOW_ON_BOARDING_KEY", "\"SHOW_ON_BOARDING\"")
        buildConfigField("String", "HAS_PIN_KEY", "\"HAS_PIN\"")
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Deps.composeVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(Deps.composeUi)
    implementation(Deps.composeUiTooling)
    implementation(Deps.composeUiToolingPreview)
    implementation(Deps.composeFoundation)
    implementation(Deps.composeMaterial)
    implementation(Deps.activityCompose)
    implementation(Deps.composeIconsExtended)
    implementation(Deps.composeNavigation)
    implementation(Deps.coilCompose)

    implementation(Deps.kotlinDateTime)

    implementation(Deps.flowLayout)
    implementation(Deps.viewPager)
    implementation(Deps.viewPagerIndicator)

    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltAndroidCompiler)
    kapt(Deps.hiltCompiler)
    implementation(Deps.hiltNavigationCompose)

    implementation(Deps.ktorAndroid)

    implementation(Deps.encPreferences)

    implementation(Deps.timber)

    androidTestImplementation(Deps.testRunner)
    androidTestImplementation(Deps.jUnit)
    androidTestImplementation(Deps.composeTesting)
    androidTestImplementation(Deps.rules)
    debugImplementation(Deps.composeTestManifest)

    kaptAndroidTest(Deps.hiltAndroidCompiler)
    androidTestImplementation(Deps.hiltTesting)
}