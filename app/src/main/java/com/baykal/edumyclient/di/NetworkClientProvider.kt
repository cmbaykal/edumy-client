package com.baykal.edumyclient.di

class NetworkClientProvider {

    //    private val TIME_OUT = 60_000

//    private val ktorHttpClient = HttpClient(Android) {
//
//        install(JsonFeature) {
//            serializer = KotlinxSerializer(Json {
//                prettyPrint = true
//                isLenient = true
//                ignoreUnknownKeys = true
//            })
//
//            engine {
//                connectTimeout = TIME_OUT
//                socketTimeout = TIME_OUT
//            }
//        }
//
//        install(Logging) {
//            logger = object : Logger {
//                override fun log(message: String) {
//                    Log.v("Logger Ktor =>", message)
//                }
//
//            }
//            level = LogLevel.ALL
//        }
//
//        install(ResponseObserver) {
//            onResponse { response ->
//                Log.d("HTTP status:", "${response.status.value}")
//            }
//        }
//
//        install(DefaultRequest) {
//            header(HttpHeaders.ContentType, ContentType.Application.Json)
//        }
//    }

    //class UserApi(private val client: HttpClient) {
//    suspend fun getUserKtor(): User = client.get("http://192.168.192.111:8080/user/info?userId=618bbcf5cd2d2d3beaa4adfd")
//}

}