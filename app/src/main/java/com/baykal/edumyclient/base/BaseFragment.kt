package com.baykal.edumyclient.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.baykal.edumyclient.base.theme.EdumyClientTheme

abstract class BaseFragment : Fragment() {

    abstract val viewModel: ViewModel

    @Composable
    abstract fun Content()

    @Composable
    abstract fun Preview()

    protected fun navigate(direction: NavDirections) = findNavController().run {
        currentDestination?.getAction(direction.actionId)?.let {
            navigate(direction)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = ComposeView(requireContext())
        view.setContent {
            EdumyClientTheme {
                Content()
            }
        }
        return view
    }

}