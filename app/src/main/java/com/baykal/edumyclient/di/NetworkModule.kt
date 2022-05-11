package com.baykal.edumyclient.di

import com.baykal.edumyclient.base.network.NetworkAdapterFactory
import com.baykal.edumyclient.base.network.auth.AuthInterceptor
import com.baykal.edumyclient.base.network.auth.EdumyAuthenticator
import com.baykal.edumyclient.data.service.EdumyService
import com.baykal.edumyclient.data.service.auth.AuthService
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
import javax.inject.Named
import javax.inject.Singleton

const val BASE_URL = "http://192.168.1.111:8081"
const val TIME_OUT = 60L

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setPrettyPrinting()
        .setLenient()
        .setDateFormat("dd.MM.yyyy HH:mm:ss")
        .create()

    @Singleton
    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Named("AuthClient")
    @Singleton
    @Provides
    fun provideAuthOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .callTimeout(TIME_OUT, TimeUnit.SECONDS)
        .build()

    @Named("AuthRetrofit")
    @Singleton
    @Provides
    fun provideAuthRetrofit(
        @Named("AuthClient") okHttpClient: OkHttpClient,
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
    fun provideAuthService(
        @Named("AuthRetrofit") retrofit: Retrofit
    ): AuthService = retrofit.create(AuthService::class.java)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        edumyAuthenticator: EdumyAuthenticator
    ) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .authenticator(edumyAuthenticator)
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