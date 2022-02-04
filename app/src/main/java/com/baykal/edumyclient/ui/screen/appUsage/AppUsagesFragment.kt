package com.baykal.edumyclient.ui.screen.appUsage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.baykal.edumyclient.base.BaseFragment
import com.baykal.edumyclient.ui.screen.account.login.LoginViewModel
import com.baykal.edumyclient.ui.screen.account.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppUsagesFragment : BaseFragment() {

    override val viewModel: ViewModel by viewModels<AppUsagesViewModel>()

    @Composable
    override fun Content() {

    }
}