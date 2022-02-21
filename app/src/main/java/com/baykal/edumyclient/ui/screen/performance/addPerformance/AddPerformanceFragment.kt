package com.baykal.edumyclient.ui.screen.performance.addPerformance

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.viewModels
import com.baykal.edumyclient.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPerformanceFragment : BaseFragment() {

    override val viewModel: AddPerformanceViewModel by viewModels()

    @Composable
    override fun Content() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Text(text = "Hello world.")
        }
    }

    @Preview
    @Composable
    override fun Preview() {
        Content()
    }

}