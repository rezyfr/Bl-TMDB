package io.rezyfr.tmdb.data.utils

import okhttp3.Interceptor
import okhttp3.Response

class HttpInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestUrl = originalRequest.url.newBuilder().addQueryParameter("api_key","b98f6ed6389c1c265a847b384a37f17c").build()
        val request = originalRequest.newBuilder().url(requestUrl).build()
        return chain.proceed(request)
    }
}