package com.baykal.edumyclient.ui.screen.account.login

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.component.EButton
import com.baykal.edumyclient.base.component.ETextButton
import com.baykal.edumyclient.base.component.ETextField
import com.baykal.edumyclient.ui.screen.account.register.RegisterRoute

@Composable
fun LoginScreen(
    viewModel: LoginViewModel
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
                text = stringResource(id = R.string.app_name)
            )
        }
        Column {
            ETextField(
                label = stringResource(id = R.string.email_hint),
                onChange = viewModel::setEmail,
                success = { Patterns.EMAIL_ADDRESS.matcher(it).matches() },
                imeAction = ImeAction.Next
            )
            ETextField(
                label = stringResource(id = R.string.password_hint),
                onChange = viewModel::setPass,
                success = { it.length in 8..16 },
                passwordToggle = true,
                imeAction = ImeAction.Done,
                onAction = { viewModel.login() }
            )
            EButton(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(40.dp),
                text = "Login"
            ) {
                viewModel.login()
            }
            ETextButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.navigate_register_button),
            ) {
                viewModel.navigate(RegisterRoute.route)
            }
        }
    }
}