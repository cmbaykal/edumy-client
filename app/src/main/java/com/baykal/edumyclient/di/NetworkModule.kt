package com.baykal.edumyclient.di

import android.util.Log
import com.baykal.edumyclient.data.service.EdumyApi
import com.baykal.edumyclient.data.service.EdumyApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.DataConversion
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.converters.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton

const val TIME_OUT = 60_000

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideClient(): HttpClient = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true

            })

            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        install(ResponseObserver) {
            onResponse { response ->
                Log.d("HTTP status:", "${response.status.value}")
            }
        }

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }

    @Singleton
    @Provides
    fun provideAPI(client: HttpClient): EdumyApi = EdumyApiImpl(client)


    //class UserApi(private val client: HttpClient) {
//    suspend fun getUserKtor(): User = client.get("http://192.168.192.111:8080/user/info?userId=618bbcf5cd2d2d3beaa4adfd")
//}

}