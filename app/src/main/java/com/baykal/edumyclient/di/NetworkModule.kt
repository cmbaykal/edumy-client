package com.baykal.edumyclient.di

import com.baykal.edumyclient.base.network.AuthInterceptor
import com.baykal.edumyclient.base.network.NetworkAdapterFactory
import com.baykal.edumyclient.base.preference.EdumySession
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

const val BASE_URL = "http://192.168.1.106:8080"
const val TIME_OUT = 60L

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(
        session: EdumySession
    ) = AuthInterceptor(session)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .callTimeout(TIME_OUT, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun provideCallAdapterFactory() = NetworkAdapterFactory()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        networkAdapterFactory: NetworkAdapterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(networkAdapterFactory)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ): EdumyService = retrofit.create(EdumyService::class.java)
}