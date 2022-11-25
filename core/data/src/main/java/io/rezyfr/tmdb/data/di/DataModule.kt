package io.rezyfr.tmdb.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.rezyfr.tmdb.data.NetworkConfig
import io.rezyfr.tmdb.data.repository.TmdbRepositoryImpl
import io.rezyfr.tmdb.data.service.TmdbService
import io.rezyfr.tmdb.data.utils.HttpInterceptor
import io.rezyfr.tmdb.data.utils.adapter.NetworkResponseAdapterFactory
import io.rezyfr.tmdb.domain.repository.TmdbRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

private const val BASE_URL = "baseUrl"
private const val IMAGE_URL = "imageUrl"
private const val BACKDROP_URL = "backdropUrl"
private const val CONTENT_LENGTH = 250_000L

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    @Named(value = BASE_URL)
    fun provideBaseUrl(networkConfig: NetworkConfig): String {
        return networkConfig.baseUrl()
    }

    @Provides
    @Singleton
    @Named(value = IMAGE_URL)
    fun provideImageUrl(networkConfig: NetworkConfig): String {
        return networkConfig.imageUrl()
    }

    @Provides
    @Singleton
    @Named(value = BACKDROP_URL)
    fun provideBackdropUrl(networkConfig: NetworkConfig): String {
        return networkConfig.backdropUrl()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(networkConfig: NetworkConfig): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (networkConfig.isDev()) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun provideHttpRequestInterceptor(networkConfig: NetworkConfig): HttpInterceptor {
        return HttpInterceptor(networkConfig)
    }

    @Provides
    @Singleton
    fun provideChuckInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        val chuckerCollector = ChuckerCollector(
            context = context,
            // Toggles visibility of the push notification
            showNotification = true,
            // Allows to customize the retention period of collected data
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )

        return ChuckerInterceptor.Builder(context)
            // The previously created Collector
            .collector(chuckerCollector)
            // The max body content length in bytes, after this responses will be truncated.
            .maxContentLength(CONTENT_LENGTH)
            // List of headers to replace with ** in the Chucker UI
            // Read the whole response body even when the client does not consume the response completely.
            // This is useful in case of parsing errors or when the response body
            // is closed before being read like in Retrofit with Void and Unit types.
            .alwaysReadResponseBody(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
        httpRequestInterceptor: HttpInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(httpRequestInterceptor)
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        networkConfig: NetworkConfig
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(networkConfig.baseUrl())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideRepository(
        tmdbService: TmdbService,
        networkConfig: NetworkConfig
    ): TmdbRepository = TmdbRepositoryImpl(tmdbService, networkConfig)

    @Provides
    @Singleton
    fun provideMuviService(
        retrofit: Retrofit
    ): TmdbService {
        return retrofit.create(TmdbService::class.java)
    }
}