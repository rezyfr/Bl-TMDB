package io.rezyfr.tmdb.data.utils

import io.rezyfr.tmdb.data.NetworkConfig
import okhttp3.Interceptor
import okhttp3.Response

class HttpInterceptor(
    private val networkConfig: NetworkConfig
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestUrl = originalRequest.url.newBuilder()
            .addQueryParameter("api_key", networkConfig.apiKey())
            .build()
        val request = originalRequest.newBuilder().url(requestUrl).build()
        return chain.proceed(request)
    }
}