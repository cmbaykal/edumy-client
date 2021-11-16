package com.baykal.edumyclient.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.baykal.edumyclient.ui.theme.EdumyClientTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EdumyClientTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }

    }

}

//class UserApi(private val client: HttpClient) {
//    suspend fun getUserKtor(): User = client.get("http://192.168.192.111:8080/user/info?userId=618bbcf5cd2d2d3beaa4adfd")
//}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EdumyClientTheme {
        Greeting("Android")
    }
}