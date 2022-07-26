package com.codebaron.planetcomics.hiltinjection

import com.codebaron.planetcomics.repository.ApiEndPoints
import com.codebaron.planetcomics.repository.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ComicModule {

    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = BASE_URL.toHttpUrl()

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseUrl") baseUrl: HttpUrl): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun provideComicResponse(retrofit: Retrofit): ApiEndPoints =
        retrofit.create(ApiEndPoints::class.java)
}