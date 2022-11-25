import io.rezyfr.tmdb.plugin.TmdbPlugin
import com.android.build.gradle.internal.tasks.factory.dependsOn

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android-extensions")
    id("io.rezyfr.tmdb.gradle.tmdb-plugin")
    id("com.google.dagger.hilt.android")
}

android {
    compileSdk = TmdbPlugin.configVersion.compileSdk
    defaultConfig {
        minSdk = TmdbPlugin.configVersion.minSdk
        targetSdk = TmdbPlugin.configVersion.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "APP_TYPE", String.format("\"%s\"", "debug"))
            isTestCoverageEnabled = true
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    flavorDimensions("api")
    productFlavors {
        create("development") {
            dimension = "api"
        }
        create("production") {
            dimension = "api"
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
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(TmdbPlugin.jetbrains.kotlinstdlibjdk7)
    TmdbPlugin.androidX.implementation.forEach { implementation(it) }
    implementation(TmdbPlugin.androidX.paging)
    TmdbPlugin.coroutines.implementation.forEach { implementation(it) }
    implementation(TmdbPlugin.hilt.hiltAndroid)
    kapt(TmdbPlugin.hilt.hiltCompiler)
    TmdbPlugin.networking.implementation.forEach { implementation(it) }

    implementation(TmdbPlugin.imageLoader.glide)
    kapt(TmdbPlugin.imageLoader.glideCompiler)

    debugImplementation(TmdbPlugin.networking.chuckLibrary)
    releaseImplementation(TmdbPlugin.networking.chuckLibraryNoOp)

    implementation(TmdbPlugin.thirdPartyLibraryDependencies.timber)

    TmdbPlugin.testDependencies.testImplementation.forEach { testImplementation(it) }
    TmdbPlugin.testDependencies.androidTestImplementation.forEach { androidTestImplementation(it) }
}

task("printVersionName") {
    doLast {
        println("${android.defaultConfig.versionName}")
    }
}