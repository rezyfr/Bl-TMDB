package io.rezyfr.tmdb.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.rezyfr.tmdb.domain.repository.TmdbRepository
import io.rezyfr.tmdb.domain.usecase.DiscoverMoviesUseCase
import io.rezyfr.tmdb.domain.usecase.GetMovieDetailUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {
    @Provides
    @Named(value = "IoDispatchers")
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @ViewModelScoped
    @Provides
    fun provideDiscoverUseCase(repository: TmdbRepository) = DiscoverMoviesUseCase(repository)

    @ViewModelScoped
    @Provides
    fun provideGetMovieDetailUseCase(
        repository: TmdbRepository,
        @Named(value = "IoDispatchers") coroutineDispatcher: CoroutineDispatcher
    ) = GetMovieDetailUseCase(repository, coroutineDispatcher)
}