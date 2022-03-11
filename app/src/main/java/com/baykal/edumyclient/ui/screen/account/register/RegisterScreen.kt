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
import androidx.compose.ui.unit.dp
import com.baykal.edumyclient.base.component.EButton
import com.baykal.edumyclient.base.component.ECheckbox
import com.baykal.edumyclient.base.component.EDateField
import com.baykal.edumyclient.base.component.ETextField

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(
                start = 32.dp,
                end = 32.dp,
                top = 10.dp,
                bottom = 10.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        ETextField(
            label = "Name",
            onChange = viewModel::setName,
            success = { it.length in 3..30 },
            imeAction = ImeAction.Next
        )
        ETextField(
            label = "Surname",
            onChange = viewModel::setSurname,
            success = { it.length in 3..30 },
            imeAction = ImeAction.Next
        )
        ETextField(
            label = "E-Mail",
            onChange = viewModel::setMail,
            success = { Patterns.EMAIL_ADDRESS.matcher(it).matches() },
            imeAction = ImeAction.Next
        )
        EDateField(
            label = "Birth",
            onChange = viewModel::setBirth,
        )
        ETextField(
            label = "Password",
            onChange = viewModel::setPass,
            success = { it.length in 8..16 },
            passwordToggle = true,
            imeAction = ImeAction.Next
        )
        ETextField(
            label = "Confirm Password",
            onChange = viewModel::setPassConfirm,
            success = { viewModel.checkPassword(it) },
            passwordToggle = true,
            imeAction = ImeAction.Done,
            onAction = { viewModel.register() }
        )
        ECheckbox(label = "Register as Teacher") {
            viewModel.setRole(it)
        }
        EButton(text = "Register") {
            viewModel.register()
        }
    }
}
