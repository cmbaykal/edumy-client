package com.baykal.edumyclient.ui.screen.account.register

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.baykal.edumyclient.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment() {

    override val viewModel: ViewModel by viewModels<RegisterViewModel>()

    @Composable
    override fun Content() {

    }
}