package com.baykal.edumyclient.ui.screen.account.register

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baykal.edumyclient.base.component.EButton
import com.baykal.edumyclient.base.component.EDateField
import com.baykal.edumyclient.base.component.ETextField
import com.baykal.edumyclient.base.ui.theme.EdumyClientTheme

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel? = null
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
        ETextField(
            label = "Name",
            onChange = { viewModel?.setName(it) },
            success = { it.length in 3..30 },
            imeAction = ImeAction.Next
        )
        ETextField(
            label = "Surname",
            onChange = { viewModel?.setSurname(it) },
            success = { it.length in 3..30 },
            imeAction = ImeAction.Next
        )
        ETextField(
            label = "E-Mail",
            onChange = { viewModel?.setMail(it) },
            success = { Patterns.EMAIL_ADDRESS.matcher(it).matches() },
            imeAction = ImeAction.Next
        )
        EDateField(
            label = "Birth",
            onChange = { viewModel?.setBirth(it) },
        )
        ETextField(
            label = "Password",
            onChange = { viewModel?.setPass(it) },
            success = { it.length in 8..16 },
            passwordToggle = true,
            imeAction = ImeAction.Next
        )
        ETextField(
            label = "Confirm Password",
            onChange = { viewModel?.setPassConfirm(it) },
            success = { viewModel?.checkPassword(it) ?: false },
            passwordToggle = true,
            imeAction = ImeAction.Done,
            onAction = { viewModel?.register() }
        )
        EButton(text = "Register") {
            viewModel?.register()
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    EdumyClientTheme {
        RegisterScreen()
    }
}
