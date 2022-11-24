package io.rezyfr.tmdb.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.rezyfr.tmdb.data.repository.TmdbRepositoryImpl
import io.rezyfr.tmdb.data.service.TmdbService
import io.rezyfr.tmdb.domain.repository.TmdbRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideRepository(
        tmdbService: TmdbService
    ): TmdbRepository = TmdbRepositoryImpl(tmdbService)

    @Provides
    @Singleton
    fun provideMuviService(
        retrofit: Retrofit
    ): TmdbService {
        return retrofit.create(TmdbService::class.java)
    }
}