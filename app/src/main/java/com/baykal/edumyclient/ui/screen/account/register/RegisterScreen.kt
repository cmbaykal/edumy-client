package com.baykal.edumyclient.ui.screen.account.register

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun RegisterScreen(navController: NavController? = null) {
    Text(text = "Register Screen")
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}