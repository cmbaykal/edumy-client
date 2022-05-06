package com.baykal.edumyclient.ui.screen.meetingSection.meetings

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.component.EFab
import com.baykal.edumyclient.base.component.EList
import com.baykal.edumyclient.base.component.ListSwipeRefreshSettings
import com.baykal.edumyclient.base.component.ListType
import com.baykal.edumyclient.data.model.user.response.UserRole
import com.baykal.edumyclient.ui.component.MeetingCard
import com.baykal.edumyclient.ui.screen.meetingSection.meetingSession.MeetingActivity
import com.baykal.edumyclient.ui.screen.meetingSection.scheduleMeeting.ScheduleMeetingRoute

@Composable
fun MeetingsScreen(
    viewModel: MeetingsViewModel
) {
    val context = LocalContext.current
    val viewState by viewModel.uiState.collectAsState()

    with(viewState) {
        LaunchedEffect(meetings) {
            if (meetings == null)
                viewModel.fetchData()
        }

        Scaffold(
            floatingActionButton = {
                if (userRole == UserRole.Teacher) {
                    EFab(onClick = {
                        viewModel.navigate(ScheduleMeetingRoute.route)
                    })
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = dimensionResource(id = R.dimen.padding_big),
                        end = dimensionResource(id = R.dimen.padding_big)
                    )
            ) {
                EList(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = dimensionResource(id = R.dimen.padding_standard)),
                    listType = ListType.Grid(spanCount = 2),
                    swipeRefreshSettings = ListSwipeRefreshSettings(enabled = true, onRefresh = viewModel::fetchData),
                    listItems = meetings
                ) { item ->
                    MeetingCard(
                        modifier = Modifier.padding(
                            start = dimensionResource(id = R.dimen.padding_standard),
                            end = dimensionResource(id = R.dimen.padding_standard),
                            top = dimensionResource(id = R.dimen.padding_medium),
                            bottom = dimensionResource(id = R.dimen.padding_medium)
                        ),
                        meeting = item,
                        onClick = {
                            val intent = Intent(context, MeetingActivity::class.java)
                            intent.putExtra("meeting", item)
                            context.startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}