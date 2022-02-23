package com.baykal.edumyclient.ui.screen.account.register

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.baykal.edumyclient.base.component.EButton
import com.baykal.edumyclient.base.component.EDateField
import com.baykal.edumyclient.base.component.ETextField

@Composable
fun RegisterScreen(viewModel: RegisterViewModel) {

    val viewState = viewModel.uiValue
    val scrollState = rememberScrollState()

    Surface(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)) {
        Column(
            modifier = Modifier
                .padding(32.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            viewModel.run {
                ETextField(
                    label = "Name",
                    onChange = ::setName,
                    success = { it.length in 3..30 },
                    imeAction = ImeAction.Next
                )
                ETextField(
                    label = "Surname",
                    onChange = ::setSurname,
                    success = { it.length in 3..30 },
                    imeAction = ImeAction.Next
                )
                ETextField(
                    label = "E-Mail",
                    onChange = ::setMail,
                    success = { Patterns.EMAIL_ADDRESS.matcher(it).matches() },
                    imeAction = ImeAction.Next
                )
                EDateField(
                    label = "Birth",
                    onChange = ::setBirth,

                    )
                ETextField(
                    label = "Password",
                    onChange = ::setPass,
                    success = { it.length in 8..16 },
                    passwordToggle = true,
                    imeAction = ImeAction.Next
                )
                ETextField(
                    label = "Confirm Password",
                    onChange = ::setPassConfirm,
                    success = { it == viewState.pass.text },
                    passwordToggle = true,
                    imeAction = ImeAction.Done,
                    onAction = ::register
                )
                EButton(text = "Register") {
                    viewModel.register()
                }
            }
        }
    }
}