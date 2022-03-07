package com.baykal.edumyclient.ui.screen.account.login

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baykal.edumyclient.base.component.EButton
import com.baykal.edumyclient.base.component.ETextButton
import com.baykal.edumyclient.base.component.ETextField
import com.baykal.edumyclient.base.ui.theme.EdumyClientTheme
import com.baykal.edumyclient.ui.screen.account.register.RegisterRoute


@Composable
fun LoginScreen(
    viewModel: LoginViewModel? = null
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                text = "Edumy Mobile",
            )
        }
        Column {
            ETextField(
                label = "E-mail",
                onChange = { viewModel?.setEmail(it) },
                success = { Patterns.EMAIL_ADDRESS.matcher(it).matches() },
                imeAction = ImeAction.Next
            )
            ETextField(
                label = "Password",
                onChange = { viewModel?.setPass(it) },
                success = { it.length in 8..16 },
                passwordToggle = true,
                imeAction = ImeAction.Done,
                onAction = { viewModel?.login() }
            )
            EButton(
                text = "Login",
            ) {
                viewModel?.login()
            }
            ETextButton(
                text = "Don't you have an Edumy account?",
            ) {
                viewModel?.controller?.navigateToRoute(RegisterRoute.route)
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    EdumyClientTheme {
        LoginScreen()
    }
}
