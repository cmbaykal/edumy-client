package com.baykal.edumyclient.ui.screen.account.register

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.component.*
import com.baykal.edumyclient.base.extension.toBirth

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel
) {
    val viewState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    val dateDialogState = remember { mutableStateOf(false) }

    with(viewState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(
                    start = dimensionResource(id = R.dimen.padding_huge),
                    end = dimensionResource(id = R.dimen.padding_huge),
                    top = dimensionResource(id = R.dimen.padding_standard),
                    bottom = dimensionResource(id = R.dimen.padding_standard)
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            ETextField(
                label = stringResource(id = R.string.name_hint),
                onChange = viewModel::setName,
                success = { it.length in 3..30 },
                imeAction = ImeAction.Next
            )
            ETextField(
                label = stringResource(id = R.string.surname_hint),
                onChange = viewModel::setSurname,
                success = { it.length in 3..30 },
                imeAction = ImeAction.Next
            )
            ETextField(
                label = stringResource(id = R.string.email_hint),
                onChange = viewModel::setMail,
                success = { Patterns.EMAIL_ADDRESS.matcher(it).matches() },
                imeAction = ImeAction.Next
            )
            EDialogField(
                label = stringResource(id = R.string.birth_hint),
                value = birth.text,
                dialogState = dateDialogState,
                onClick = {
                    dateDialogState.value = true
                },
                onDismiss = {
                    dateDialogState.value = false
                }
            ) {
                EDatePicker(
                    onChange = { d ->
                        viewModel.setBirth(InputState(text = d.toBirth))
                    }
                )
            }
            ETextField(
                label = stringResource(id = R.string.password_hint),
                onChange = viewModel::setPass,
                success = { it.length in 8..16 },
                passwordToggle = true,
                imeAction = ImeAction.Next
            )
            ETextField(
                label = stringResource(id = R.string.confirm_password_hint),
                onChange = viewModel::setPassConfirm,
                success = { viewModel.checkPassword(it) },
                passwordToggle = true,
                imeAction = ImeAction.Done,
                onAction = { viewModel.register() }
            )
            ECheckbox(
                label = stringResource(id = R.string.register_as_teacher_checkbox)
            ) {
                viewModel.setRole(it)
            }
            EButton(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_standard))
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.button_height_standard)),
                text = stringResource(id = R.string.register_button)
            ) {
                viewModel.register()
            }
        }
    }
}
