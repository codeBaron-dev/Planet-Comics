package com.codebaron.planetcomics.hiltinjection

import com.codebaron.planetcomics.mvvm.ComicRepository
import com.codebaron.planetcomics.mvvm.ComicRepositoryImplementation
import com.codebaron.planetcomics.repository.ApiEndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module @InstallIn(SingletonComponent::class)
class ComicProvider {
    @Provides @Singleton
    fun provideComics(apiEndPoints: ApiEndPoints): ComicRepository = ComicRepositoryImplementation(apiEndPoints)
}