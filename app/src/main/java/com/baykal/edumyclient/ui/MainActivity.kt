package com.baykal.edumyclient.ui

import android.os.Bundle


import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.baykal.edumyclient.ui.navigation.Screen
import com.baykal.edumyclient.ui.screen.HomeScreen
import com.baykal.edumyclient.ui.screen.account.login.LoginScreen
import com.baykal.edumyclient.ui.screen.account.register.RegisterScreen

import com.baykal.edumyclient.ui.theme.EdumyClientTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EdumyClientTheme {
                Surface(color = MaterialTheme.colors.background) {
                    EdumyClient()
                }
            }
        }
    }
}

@Composable
fun EdumyClient() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.Register.route) { RegisterScreen(navController) }
        composable(Screen.Home.route) { HomeScreen()}
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EdumyClientTheme {
        EdumyClient()
    }
}