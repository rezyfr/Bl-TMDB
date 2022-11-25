object AndroidX {
    const val androidXLegacyV4 = "androidx.legacy:legacy-support-v4:${Versions.androidXLegacyV4}"
    const val androidXMaterial = "com.google.android.material:material:${Versions.googleMaterial}"
    const val androidXRecyclerView =
        "androidx.recyclerview:recyclerview:${Versions.androidXRecyclerView}"
    const val androidXCardView = "androidx.cardview:cardview:${Versions.androidXCardView}"
    const val androidXAppCompat = "androidx.appcompat:appcompat:${Versions.androidXAppcompat}"
    const val androidXConstraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.androidXConstraintLayout}"
    const val androidXWork =
        "androidx.work:work-runtime-ktx:${Versions.androidXWorkManager}"
    const val androidKTX = "androidx.core:core-ktx:${Versions.androidXCoreKtx}"
    const val androidActivityKTX = "androidx.activity:activity-ktx:${Versions.androidXActivityKtx}"
    const val androidFragmentKTX = "androidx.fragment:fragment-ktx:${Versions.androidXFragmentKtx}"
    const val swipeRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.androidXSwipeRefreshLayout}"
    const val paging = "androidx.paging:paging-runtime-ktx:${Versions.androidXPaging}"
    val implementation = arrayOf(
        androidXLegacyV4,
        androidXMaterial,
        androidXRecyclerView,
        androidXCardView,
        androidXAppCompat,
        androidXConstraintLayout,
        androidKTX,
        androidXWork,
        androidActivityKTX,
        androidFragmentKTX,
        swipeRefreshLayout
    )
}

object AndroidXLifecycle {
    const val androidXLifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.androidXLifecycleExt}"
    const val androidXLifecycleCompiler =
        "androidx.lifecycle:lifecycle-compiler:${Versions.androidXLifecycleExt}"

    val implementation = arrayOf(
        androidXLifecycleExtensions
    )

    val kapt = arrayOf(
        androidXLifecycleCompiler
    )
}

object AndroidXLifecycleScope {
    const val viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidXLifecycleScope}"
    const val runtime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidXLifecycleScope}"

    val implementation = arrayOf(
        viewmodel,
        runtime
    )
}

object AndroidXLifecycleLivedata {
    const val livedata =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidXLivedata}"
}

object Networking {
    const val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
    const val okhttp3LoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
    const val retrofit2ConverterScalars =
        "com.squareup.retrofit2:converter-scalars:${Versions.retrofit2}"
    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
    const val retrofit2GsonConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit2}"
    const val retrofit2Mock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit2}"
    const val chuckLibrary =
        "com.github.chuckerteam.chucker:library:${ThirdPartyLibVersions.chucker}"
    const val chuckLibraryNoOp =
        "com.github.chuckerteam.chucker:library-no-op:${ThirdPartyLibVersions.chucker}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    val implementation = arrayOf(
        okhttp3,
        okhttp3LoggingInterceptor,
        retrofit2,
        retrofit2GsonConverter,
        retrofit2ConverterScalars
    )

    val debugImplementation = arrayOf(
        chuckLibrary
    )

    val releaseImplementation = arrayOf(
        chuckLibraryNoOp
    )
}

object Coroutines {
    const val coroutinecore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutineandroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutinePlayService =
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutines}"

    val implementation = arrayOf(
        coroutinecore,
        coroutineandroid
    )
}

object DaggerHilt {
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.daggerHilt}"
}

object LeakCanary {
    const val leakCanary =
        "com.squareup.leakcanary:leakcanary-android:${ThirdPartyLibVersions.leakCanary}"

    val debugImplementation = arrayOf(
        leakCanary
    )
}

object ImageLoader {
    const val glide = "com.github.bumptech.glide:glide:${ThirdPartyLibVersions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${ThirdPartyLibVersions.glide}"
}

object TestDependencies {
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockito}"
    const val mockitoKotlin =
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val json = "org.json:json:${Versions.json}"
    const val powerMockitoJunit4 = "org.powermock:powermock-module-junit4:${Versions.powerMockito}"
    const val powerMockito2 = "org.powermock:powermock-api-mockito2:${Versions.powerMockito}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
    const val androidXCoreTesting =
        "androidx.arch.core:core-testing:${Versions.androidXCoreTesting}"
    const val androidXTestCore = "androidx.test:core:${Versions.androidXTestCore}"
    const val androidXTestRules = "androidx.test:rules:${Versions.androidXTestRules}"
    const val androidXTestJunit = "androidx.test.ext:junit:${Versions.androidXTestJUnit}"
    const val androidXTestEspresso =
        "androidx.test.espresso:espresso-core:${Versions.androidXTestEspresso}"
    const val androidXTestRunner = "androidx.test:runner:${Versions.androidXTestRunner}"
    const val androidXTestTruth = "androidx.test.ext:truth:${Versions.androidXTestTruth}"
    const val turbine = "app.cash.turbine:turbine:${ThirdPartyLibVersions.turbine}"
    const val threetenabp = "org.threeten:threetenbp:${ThirdPartyLibVersions.testThreetenabp}"
    const val regularThreetenabp =
        "org.threeten:threetenbp:${ThirdPartyLibVersions.regularThreetenabp}"

    val testImplementation = arrayOf(
        jUnit,
        androidXCoreTesting,
        mockitoCore,
        mockitoKotlin,
        mockitoInline,
        json,
        coroutinesTest,
        powerMockitoJunit4,
        powerMockito2,
        androidXTestTruth,
        turbine,
        threetenabp,
        regularThreetenabp
    )

    val androidTestImplementation = arrayOf(
        jUnit,
        androidXTestCore,
        androidXTestRules,
        androidXTestJunit,
        androidXTestEspresso,
        androidXTestRunner,
        coroutinesTest,
        androidXTestTruth
    )
}

object Jetbrains {
    const val kotlinstdlibjdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinJdk}"
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinJdk}"
}

object ThirdPartyLibraryDependencies {
    const val timber = "com.jakewharton.timber:timber:${ThirdPartyLibVersions.timber}"
}

object Modules {
    const val data = ":core:data"
    const val domain = ":core:domain"

    val implementation = arrayOf(
        data, domain
    )
}