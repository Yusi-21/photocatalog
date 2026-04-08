package com.success.photocatalog.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
//import com.success.nobel.data.api.PhotoApi
import com.success.photocatalog.data.api.PromoImageApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
//    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private const val BASE_URL = "http://192.168.31.116:8080/"
    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true // более гибкий парсинг
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit {
        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

//    @Provides
//    @Singleton
//    fun providePhotoApi(retrofit: Retrofit): PhotoApi {
//        return retrofit.create(PhotoApi::class.java)
//    }

    @Provides
    @Singleton
    fun providePromoImageApi(retrofit: Retrofit): PromoImageApi {
        return retrofit.create(PromoImageApi::class.java)
    }
}