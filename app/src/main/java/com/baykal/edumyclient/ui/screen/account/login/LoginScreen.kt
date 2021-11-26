package com.baykal.edumyclient.ui.screen.account.login

import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.baykal.edumyclient.ui.component.EButton
import com.baykal.edumyclient.ui.component.ETextButton
import com.baykal.edumyclient.ui.component.ETextField
import com.baykal.edumyclient.ui.component.InputState
import com.baykal.edumyclient.ui.navigation.Screen

@Composable
fun LoginScreen(navController: NavController? = null) {

    val focusManager = LocalFocusManager.current
    var email by remember { mutableStateOf(InputState()) }
    var password by remember { mutableStateOf(InputState()) }

    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier.weight(1.5f, true),
            contentAlignment = Alignment.Center
        ) {
            Text(
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                text = "Edumy Mobile",
            )
        }
        Column(
            modifier = Modifier.weight(2.5f, true)
        ) {
            ETextField(
                label = "E-mail",
                onChange = { email = it },
                success = { Patterns.EMAIL_ADDRESS.matcher(it).matches() },
                errorText = "Boş olamaz"
            )
            ETextField(
                label = "Password",
                onChange = { password = it },
                success = { it.length in 8..16 }
            )
            EButton(
                text = "Login",
            ) {
                focusManager.clearFocus()
                Log.d("LoginNavigation","mail : " + email.isError + " pass : " + password.isError)
                if (!email.isError && !password.isError) {
                    navController?.navigate(Screen.Home.route)
                }
            }
            ETextButton(
                text = "Don't you have an Edumy account?",
            ) {
                navController?.navigate(Screen.Register.route)
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}