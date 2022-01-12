package com.baykal.edumyclient.ui.screen.account.register

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.baykal.edumyclient.data.model.account.user.RegisterCredentials
import com.baykal.edumyclient.ui.component.*

@Composable
fun RegisterScreen(navController: NavController? = null) {

    val viewModel: RegisterViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    var name by remember { mutableStateOf(InputState()) }
    var surname by remember { mutableStateOf(InputState()) }
    var mail by remember { mutableStateOf(InputState()) }
    var pass by remember { mutableStateOf(InputState()) }
    var birth by remember { mutableStateOf(InputState()) }
    var passConfirm by remember { mutableStateOf(InputState()) }

    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        ETextField(
            label = "Name",
            onChange = { name = it },
            success = { it.length in 3..30 }
        )
        ETextField(
            label = "Surname",
            onChange = { surname = it },
            success = { it.length in 3..30 }

        )
        ETextField(
            label = "E-Mail",
            onChange = { mail = it },
            success = { Patterns.EMAIL_ADDRESS.matcher(it).matches() }
        )
        EDateField(
            label = "Birth",
            onChange = { birth = it }
        )
        ETextField(
            label = "Password",
            onChange = { pass = it },
            success = { it.length in 8..16 },
            passwordToggle = true
        )
        ETextField(
            label = "Confirm Password",
            onChange = { passConfirm = it },
            success = { it == pass.text },
            passwordToggle = true
        )
        EButton(text = "Register") {
            if (!(name.isError && surname.isError && mail.isError && pass.isError && passConfirm.isError && birth.isError)) {
                val credentials = RegisterCredentials(
                    role = "student",
                    mail = mail.text,
                    pass = pass.text,
                    name = name.text + " " + surname.text,
                    birth = birth.text + " 00.00.00",
                )

                viewModel.register(credentials)
            }
        }
    }

    when {
        uiState.loading -> {
            LoadingIndicator()
        }

        uiState.success -> {
            GenericDialog(
                title = "Register",
                message = "Register process is successful"
            )
        }

        uiState.error -> {
            GenericDialog(
                title = "Error",
                message = "Oops, an error occurred. Please try again."
            )
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}