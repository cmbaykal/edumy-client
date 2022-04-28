package com.baykal.edumyclient.ui.screen.account.update

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
                    value = it.name.toString(),
                    onChange = viewModel::setName,
                    success = { it.length in 3..30 },
                    imeAction = ImeAction.Next
                )
                ETextField(
                    label = stringResource(id = R.string.biography_hint),
                    value = it.bio ?: "",
                    maxLines = 4,
                    onChange = viewModel::setBio,
                    success = { true },
                    imeAction = ImeAction.Next
                )
                EButton(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_standard))
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.button_height_standard)),
                    text = stringResource(id = R.string.update_button)
                ) {
                    viewModel.updateUser()
                }
                ETextButton(text = stringResource(id = R.string.change_password_button)) {
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
        title = stringResource(id = R.string.change_password_dialog_title),
        positiveButton = positiveButton,
        negativeButton = negativeButton
    ) {
        ETextField(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.padding_standard),
                end = dimensionResource(id = R.dimen.padding_standard)
            ),
            label = stringResource(id = R.string.current_password_hint),
            onChange = viewModel::setOldPass,
            success = { it.length in 8..16 },
            passwordToggle = true,
            imeAction = ImeAction.Next
        )
        ETextField(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.padding_standard),
                end = dimensionResource(id = R.dimen.padding_standard)
            ),
            label = stringResource(id = R.string.new_password_hint),
            onChange = viewModel::setPass,
            success = { it.length in 8..16 },
            passwordToggle = true,
            imeAction = ImeAction.Next
        )
        ETextField(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.padding_standard),
                end = dimensionResource(id = R.dimen.padding_standard)
            ),
            label = stringResource(id = R.string.confirm_new_password_hint),
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
