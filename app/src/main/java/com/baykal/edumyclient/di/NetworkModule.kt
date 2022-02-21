package com.baykal.edumyclient.di

import com.baykal.edumyclient.data.service.EdumyService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val BASE_URL = ""
const val TIME_OUT = 60L

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGson() = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideGsonConverterFactory(gson: Gson) = GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .callTimeout(TIME_OUT, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ) = retrofit.create(EdumyService::class.java)

}