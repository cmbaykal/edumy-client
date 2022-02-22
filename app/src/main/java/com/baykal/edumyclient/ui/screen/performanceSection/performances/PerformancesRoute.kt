package com.baykal.edumyclient.ui.screen.performanceSection.performances

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.baykal.edumyclient.base.nav.NavRoute

object PerformancesRoute : NavRoute<PerformancesViewModel> {
    override val title = "Performances"
    override val route = "performances"
    override fun bottomBarVisibility() = true
    override fun topBarVisibility() = true

    @Composable
    override fun Content(viewModel: PerformancesViewModel) {
        PerformancesScreen(viewModel)
    }

    @Composable
    override fun viewModel(): PerformancesViewModel = hiltViewModel()
}