package com.baykal.edumyclient.ui.screen.account.login

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import com.baykal.edumyclient.base.BaseFragment
import com.baykal.edumyclient.base.component.EButton
import com.baykal.edumyclient.base.component.ETextButton
import com.baykal.edumyclient.base.component.ETextField
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    override val viewModel: LoginViewModel by viewModels()

    @Composable
    override fun Content() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Box(
                    modifier = Modifier.weight(1.5f, true),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        fontSize = 40.sp,
                        fontWeight = FontWeight.ExtraBold,
                        text = "Edumy Mobile",
                    )
                }
                Column(
                    modifier = Modifier.weight(2.5f, true)
                ) {
                    ETextField(
                        label = "E-mail",
                        onChange = viewModel::setEmail,
                        success = { Patterns.EMAIL_ADDRESS.matcher(it).matches() },
                        imeAction = ImeAction.Next
                    )
                    ETextField(
                        label = "Password",
                        onChange = viewModel::setPass,
                        success = { it.length in 8..16 },
                        passwordToggle = true,
                        imeAction = ImeAction.Done,
                        onAction = { viewModel.login() }
                    )
                    EButton(
                        text = "Login",
                    ) {
                        viewModel.login()
                    }
                    ETextButton(
                        text = "Don't you have an Edumy account?",
                    ) {
                        navigate(LoginFragmentDirections.LoginToRegister())
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    override fun Preview() {
        Content()
    }

}