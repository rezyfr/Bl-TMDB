package io.rezyfr.tmdb.plugin

import AndroidConfigVersion
import AndroidX
import AndroidXLifecycle
import AndroidXLifecycleLivedata
import AndroidXLifecycleScope
import Coroutines
import DaggerHilt
import ImageLoader
import Jetbrains
import LeakCanary
import Networking
import TestDependencies
import ThirdPartyLibraryDependencies
import Versions
import org.gradle.api.Plugin
import org.gradle.api.Project

class TmdbPlugin : Plugin<Project>{
    override fun apply(target: Project) {
        // for common dependencies or empty
    }

    companion object {
        val versions = Versions
        val configVersion = AndroidConfigVersion
        val androidX = AndroidX
        val androidXLifecycle = AndroidXLifecycle
        val androidXLifecycleLivedata = AndroidXLifecycleLivedata
        val androidXLifecycleScope = AndroidXLifecycleScope
        val networking = Networking
        val coroutines = Coroutines
        val hilt = DaggerHilt
        val leakCanary = LeakCanary
        val imageLoader = ImageLoader
        val testDependencies = TestDependencies
        val jetbrains = Jetbrains
        val thirdPartyLibraryDependencies = ThirdPartyLibraryDependencies
    }
}