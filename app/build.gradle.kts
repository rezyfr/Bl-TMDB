import io.rezyfr.tmdb.plugin.TmdbPlugin
import com.android.build.gradle.internal.tasks.factory.dependsOn

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    kotlin("kapt")
    id("io.rezyfr.tmdb.gradle.tmdb-plugin")
    id("com.google.dagger.hilt.android")
}

fun buildProperty(key: String, format: Boolean = false): String {
    return if (format) {
        String.format("\"%s\"", project.property(key) as String)
    } else {
        project.property(key) as String
    }
}

android {
    compileSdk = TmdbPlugin.configVersion.compileSdk
    buildToolsVersion = TmdbPlugin.configVersion.buildTools
    defaultConfig {
        applicationId = "io.rezyfr.tmdb"
        minSdk = TmdbPlugin.configVersion.minSdk
        targetSdk = TmdbPlugin.configVersion.targetSdk
        versionCode = TmdbPlugin.configVersion.versionCode
        versionName = TmdbPlugin.configVersion.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "APP_TYPE", String.format("\"%s\"", "debug"))
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            versionNameSuffix = "-DEBUG"
            isTestCoverageEnabled = true
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            buildConfigField("String", "APP_TYPE", String.format("\"%s\"", "release"))
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
            applicationIdSuffix = ".debug"
            buildConfigField("String", "BASE_URL", buildProperty("BASE_URL_DEBUG"))
            buildConfigField("String", "DOWNLOAD_URL", buildProperty("BASE_IMAGE_URL_DOWNLOAD"))
            buildConfigField("String", "DOWNLOAD_URL", buildProperty("BASE_BACKDROP_URL_DOWNLOAD"))
            buildConfigField("String", "API_KEY", buildProperty("API_KEY"))
        }
        create("production") {
            dimension = "api"
            applicationIdSuffix = ".release"
            buildConfigField("String", "BASE_URL", buildProperty("BASE_URL_DEBUG"))
            buildConfigField("String", "DOWNLOAD_URL", buildProperty("BASE_IMAGE_URL_DOWNLOAD"))
            buildConfigField("String", "DOWNLOAD_URL", buildProperty("BASE_BACKDROP_URL_DOWNLOAD"))
            buildConfigField("String", "API_KEY", buildProperty("API_KEY"))
        }
    }

    packagingOptions {
        exclude("META-INF/rxjava.properties")
        exclude("META-INF/library_release.kotlin_module")
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

    useLibrary("android.test.runner")
    useLibrary("android.test.base")
    useLibrary("android.test.mock")

    testOptions {
        // Used for Unit testing Android dependent elements in /test folder
        unitTests.isIncludeAndroidResources  = true
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(TmdbPlugin.jetbrains.kotlinstdlibjdk7)
    TmdbPlugin.androidX.implementation.forEach { implementation(it) }
    implementation(TmdbPlugin.androidXLifecycle.androidXLifecycleExtensions)
    TmdbPlugin.androidXLifecycleScope.implementation.forEach { implementation(it) }
    implementation(TmdbPlugin.androidXLifecycleLivedata.livedata)
    TmdbPlugin.module.implementation.forEach { implementation(project(it)) }

    TmdbPlugin.coroutines.implementation.forEach { implementation(it) }
    implementation(TmdbPlugin.hilt.hiltAndroid)
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
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