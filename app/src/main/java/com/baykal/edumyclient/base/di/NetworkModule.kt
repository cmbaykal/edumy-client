package com.baykal.edumyclient.base.di

import com.baykal.edumyclient.base.data.BaseResponse
import com.baykal.edumyclient.base.network.AuthInterceptor
import com.baykal.edumyclient.base.network.EdumyAuthenticator
import com.baykal.edumyclient.data.service.AuthServiceImp
import com.baykal.edumyclient.data.service.EdumyServiceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

const val BASE_URL = "192.168.1.113:8081"
const val TIME_OUT = 60000L

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideClient(
        loggingInterceptor: HttpLoggingInterceptor
    ) = HttpClient(OkHttp) {
        Charsets {
            register(Charsets.UTF_8)
        }
        engine {
            addInterceptor(loggingInterceptor)
        }
        install(HttpTimeout) {
            requestTimeoutMillis = TIME_OUT
        }
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                prettyPrint = true
                ignoreUnknownKeys = true
            })
        }
        install(HttpCallValidator) {
            validateResponse {
                val code = it.status.value

                when {
                    code in 300..399 -> throw RedirectResponseException(it, "Redirect Error")
                    code in 400..499 -> throw ClientRequestException(it, "Client Error")
                    code in 500..599 -> throw ServerResponseException(it, "Server Error")
                    code >= 600 -> throw ResponseException(it, "Response Error")
                }
            }

            handleResponseExceptionWithRequest { cause, _ ->
                throw cause
            }
        }
    }

    @Singleton
    @Provides
    @Named("AuthClient")
    fun provideAuthClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        authenticator: EdumyAuthenticator
    ) = HttpClient(OkHttp) {
        Charsets {
            register(Charsets.UTF_8)
        }
        engine {
            addInterceptor(loggingInterceptor)
            addInterceptor(authInterceptor)
            config {
                authenticator(authenticator)
            }
        }
        install(HttpTimeout) {
            requestTimeoutMillis = TIME_OUT
        }
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                prettyPrint = true
                ignoreUnknownKeys = true
            })
        }
        install(HttpCallValidator) {
            validateResponse {
                if(!it.status.isSuccess()){
                    val code = it.status.value
                    val response:BaseResponse = it.body()

                    when {
                        code in 300..399 -> throw RedirectResponseException(it, "Redirect Error")
                        code in 400..499 -> throw Exception(response.error)
                        code in 500..599 -> throw ServerResponseException(it, "Server Error")
                        code >= 600 -> throw ResponseException(it, "Response Error")
                    }
                }
            }

            handleResponseExceptionWithRequest { cause, _ ->
                throw cause
            }
        }
    }

    @Singleton
    @Provides
    fun provideAuthService(
        httpClient: HttpClient
    ) = AuthServiceImp(httpClient)

    @Singleton
    @Provides
    fun provideService(
        @Named("AuthClient") httpClient: HttpClient
    ) = EdumyServiceImp(httpClient)

    @Singleton
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // endregion Retrofit
}