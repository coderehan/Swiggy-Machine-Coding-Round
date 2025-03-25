package com.rehan.swiggymachinecodinground.di

import com.rehan.swiggymachinecodinground.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieRepository(): MovieRepository {
        return MovieRepository()
    }
}
