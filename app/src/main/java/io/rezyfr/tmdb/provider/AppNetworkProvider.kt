package io.rezyfr.tmdb.provider

import io.rezyfr.tmdb.BuildConfig
import io.rezyfr.tmdb.data.NetworkConfig

class AppNetworkProvider : NetworkConfig() {
    override fun baseUrl(): String {
        return BuildConfig.BASE_URL
    }

    override fun imageUrl(): String {
        return BuildConfig.IMAGE_URL
    }

    override fun backdropUrl(): String {
        return BuildConfig.BACKDROP_URL
    }

    override fun timeOut(): Long {
        return 30L
    }

    override fun apiKey(): String {
        return BuildConfig.API_KEY
    }
}