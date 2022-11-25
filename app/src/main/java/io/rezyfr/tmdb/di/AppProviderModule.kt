package io.rezyfr.tmdb.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.rezyfr.tmdb.data.NetworkConfig
import io.rezyfr.tmdb.provider.AppNetworkProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppProviderModule {
    @Provides
    @Singleton
    fun provideNetworkConfig() : NetworkConfig {
        return AppNetworkProvider()
    }
}
