package com.baykal.edumyclient.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.baykal.edumyclient.ui.theme.EdumyClientTheme

abstract class BaseFragment : Fragment() {

    abstract val viewModel: ViewModel

    @Composable
    abstract fun Content()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = ComposeView(requireContext()).apply {
        if(savedInstanceState == null){
            setContent{
                Content()
            }
        }
    }
}