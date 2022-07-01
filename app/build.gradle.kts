plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        val buildProp = file(rootProject.file("build.properties"))
        applicationId = "kr.pe.ssun.composedemo"
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        versionCode = Versions.getProperty(buildProp, "versionCode").toInt()
        versionName = Versions.getProperty(buildProp, "versionName")
        println("[sunchulbaek] versionCode = $versionCode")
        println("[sunchulbaek] versionName = $versionName")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "clientId", "\"${Versions.getProperty(buildProp, "clientId")}\"")
        buildConfigField("String", "clientSecret", "\"${Versions.getProperty(buildProp, "clientSecret")}\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    api(platform(project(":depconstraints")))
    kapt(platform(project(":depconstraints")))
    androidTestApi(platform(project(":depconstraints")))

    // UI
    implementation(Libs.CORE_KTX)
    implementation(Libs.ACCOMPANIST_WEBVIEW)
    implementation(Libs.LIFECYCLE_RUNTIME_KTX)

    // Compose
    implementation(Libs.ACTIVITY_COMPOSE)
    implementation(Libs.COMPOSE_UI)
    implementation(Libs.COMPOSE_MATERIAL)
    implementation(Libs.COMPOSE_UI_TOOLING_PREVIEW)
    implementation(Libs.CONSTRAINT_LAYOUT_COMPOSE)
    implementation(Libs.LIFECYCLE_VIEW_MODEL_COMPOSE)
    debugImplementation(Libs.COMPOSE_UI_TOOLING)
    debugImplementation(Libs.COMPOSE_UI_TEST_MANIFEST)

    // Hilt
    implementation(Libs.HILT_ANDROID)
    kapt(Libs.HILT_COMPILER)

    // Navigation
    implementation(Libs.NAVIGATION_COMPOSE)

    // Retrofit
    implementation(Libs.RETROFIT)
    implementation(Libs.RETROFIT_GSON_CONVERTER)
    implementation(Libs.OKHTTP_LOGGING_INTERCEPTER)

    // Timber
    implementation(Libs.TIMBER)

    // Coil
    implementation(Libs.COIL)

    // Test
    testImplementation(Libs.JUNIT)
    androidTestImplementation(Libs.EXT_JUNIT)
    androidTestImplementation(Libs.ESPRESSO_CORE)
    androidTestImplementation(Libs.COMPOSE_UI_TEST_JUNIT)
}