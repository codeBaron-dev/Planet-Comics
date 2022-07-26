package com.codebaron.planetcomics.repository

import com.codebaron.comicapp.repository.ApiEndPoints
import com.codebaron.comicapp.repository.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class BaseServices {

    companion object{
        private var baseService: BaseServices? = null
        private var retrofit: Retrofit? = null

        fun retrofit(): Retrofit? {
            return retrofit
        }

        val instanceService: BaseServices?
            get() {
                if (baseService == null) baseService = BaseServices()
                return baseService
            }
    }

    val apiInterface: ApiEndPoints?
        get() = retrofit?.create(ApiEndPoints::class.java)

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.MINUTES)
            .writeTimeout(30, TimeUnit.MINUTES)
            .addInterceptor(
                Interceptor { chain: Interceptor.Chain ->
                    val original = chain.request()
                    val requestBuilder: Request.Builder = original.newBuilder()
                        .method(original.method, original.body)
                    val request: Request = requestBuilder.build()
                    chain.proceed(request)
                }
            ).addInterceptor(logging).build()

        val gson = GsonBuilder().setLenient().create()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient).build()
    }

}