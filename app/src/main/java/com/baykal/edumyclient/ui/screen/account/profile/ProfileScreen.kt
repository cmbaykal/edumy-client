package com.baykal.edumyclient.ui.screen.account.profile

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel
) {
    val viewState by viewModel.uiState.collectAsState()

    with(viewState) {
        user?.also {
            Text(text = it.name.toString())
        } ?: run {
            viewModel.getUserInformation()
        }
    }
}