package com.baykal.edumyclient.ui.screen.account.update

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.baykal.edumyclient.base.component.*

@Composable
fun UpdateUserScreen(
    viewModel: UpdateUserViewModel
) {
    val viewState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    var passDialogState by remember { mutableStateOf(false) }

    with(viewState) {
        LaunchedEffect(this.user) {
            if (user == null)
                viewModel.getUserInformation()
        }

        user?.let {
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
                    value = it.name.toString(),
                    onChange = viewModel::setName,
                    success = { it.length in 3..30 },
                    imeAction = ImeAction.Next
                )
                ETextField(
                    label = "Biography",
                    value = it.bio ?: "",
                    onChange = viewModel::setBio,
                    success = { true },
                    imeAction = ImeAction.Next
                )
                EButton(text = "Update") {
                    viewModel.updateUser()
                }
                ETextButton(text = "Change User Password") {
                    passDialogState = true
                }
            }
            if (passDialogState) {
                PasswordChangeDialog(viewModel) {
                    passDialogState = false
                }
            }
        }
    }
}

@Composable
fun PasswordChangeDialog(viewModel: UpdateUserViewModel, onDismiss: () -> Unit) {
    val positiveButton = DialogButton("Update") {
        viewModel.changePassword()
        onDismiss.invoke()
    }
    val negativeButton = DialogButton("Cancel", onClick = onDismiss)

    EDialog(
        title = "Change Password",
        positiveButton = positiveButton,
        negativeButton = negativeButton
    ) {
        ETextField(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
            label = "Current Password",
            onChange = viewModel::setOldPass,
            success = { it.length in 8..16 },
            passwordToggle = true,
            imeAction = ImeAction.Next
        )
        ETextField(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
            label = "New Password",
            onChange = viewModel::setPass,
            success = { it.length in 8..16 },
            passwordToggle = true,
            imeAction = ImeAction.Next
        )
        ETextField(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
            label = "New Confirm Password",
            onChange = viewModel::setPassConfirm,
            success = { viewModel.checkPassword(it) },
            passwordToggle = true,
            imeAction = ImeAction.Done,
            onAction = {
                viewModel.changePassword()
                onDismiss.invoke()
            }
        )
    }
}
