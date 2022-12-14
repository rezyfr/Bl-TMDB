import io.rezyfr.tmdb.plugin.TmdbPlugin

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
    signingConfigs {
        create("release") {
            keyAlias = "tmdbrezyfr"
            keyPassword = "tmdbrezyfr"
            storeFile = file("C:\\Users\\Use\\StudioProjects\\tmdbrezyfr.jks")
            storePassword = "tmdbrezyfr"
        }
    }
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

    signingConfigs {
        create("production") {
            keyAlias = "tmdbrezyfr"
            keyPassword = "tmdbrezyfr"
            storeFile = file("tmdbrezyfr.jks")
            storePassword = "tmdbrezyfr"
        }
    }
    buildTypes {
        debug {
            buildConfigField("String", "APP_TYPE", String.format("\"%s\"", "debug"))
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            versionNameSuffix = "-DEBUG"
            isDebuggable = true
            manifestPlaceholders["appLabel"] = "TheMovieDB Debug"
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            buildConfigField("String", "APP_TYPE", String.format("\"%s\"", "release"))
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("production")
        }
    }
    flavorDimensions("api")
    productFlavors {
        create("development") {
            dimension = "api"
            applicationIdSuffix = ".debug"
            buildConfigField("String", "BASE_URL", buildProperty("BASE_URL_DEBUG"))
            buildConfigField("String", "IMAGE_URL", buildProperty("BASE_IMAGE_URL"))
            buildConfigField("String", "BACKDROP_URL", buildProperty("BASE_BACKDROP_URL"))
            buildConfigField("String", "API_KEY", buildProperty("API_KEY"))
        }
        create("production") {
            dimension = "api"
            applicationIdSuffix = ".release"
            buildConfigField("String", "BASE_URL", buildProperty("BASE_URL_DEBUG"))
            buildConfigField("String", "IMAGE_URL", buildProperty("BASE_IMAGE_URL"))
            buildConfigField("String", "BACKDROP_URL", buildProperty("BASE_BACKDROP_URL"))
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

        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(TmdbPlugin.jetbrains.kotlinstdlibjdk7)
    TmdbPlugin.androidX.implementation.forEach { implementation(it) }
    TmdbPlugin.androidXLifecycleScope.implementation.forEach { implementation(it) }
    TmdbPlugin.module.implementation.forEach { implementation(project(it)) }

    TmdbPlugin.coroutines.implementation.forEach { implementation(it) }
    implementation(TmdbPlugin.hilt.hiltAndroid)
    implementation(TmdbPlugin.androidX.paging)
    kapt(TmdbPlugin.hilt.hiltCompiler)
    TmdbPlugin.networking.implementation.forEach { implementation(it) }

    implementation(TmdbPlugin.imageLoader.glide)
    kapt(TmdbPlugin.imageLoader.glideCompiler)

    implementation(TmdbPlugin.thirdPartyLibraryDependencies.timber)
    implementation(TmdbPlugin.thirdPartyLibraryDependencies.shimmer)

    TmdbPlugin.testDependencies.testImplementation.forEach { testImplementation(it) }
}

task("printVersionName") {
    doLast {
        println("${android.defaultConfig.versionName}")
    }
}
