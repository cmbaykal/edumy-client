package com.baykal.edumyclient.ui.screen.meetingSection.scheduleMeeting

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.baykal.edumyclient.base.nav.NavRoute

object ScheduleMeetingRoute : NavRoute<ScheduleMeetingViewModel> {

    override val title = "Schedule Meeting"
    override val route = "scheduleMeeting"

    override fun topBarVisibility() = true

    @Composable
    override fun Content(viewModel: ScheduleMeetingViewModel) {
        ScheduleMeetingScreen(viewModel)
    }

    @Composable
    override fun viewModel(): ScheduleMeetingViewModel = hiltViewModel()
}