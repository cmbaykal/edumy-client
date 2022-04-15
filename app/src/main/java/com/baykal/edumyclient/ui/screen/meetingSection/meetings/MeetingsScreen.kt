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
import androidx.compose.ui.unit.dp
import com.baykal.edumyclient.base.component.EFab
import com.baykal.edumyclient.base.component.EList
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
                        start = 20.dp,
                        end = 20.dp
                    )
            ) {
                EList(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp),
                    swipeRefresh = true,
                    onRefresh = viewModel::fetchData,
                    listItems = meetings
                ) { item ->
                    MeetingCard(
                        modifier = Modifier.padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = 6.dp,
                            bottom = 6.dp
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