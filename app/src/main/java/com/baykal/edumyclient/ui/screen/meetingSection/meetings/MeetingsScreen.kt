package com.baykal.edumyclient.ui.screen.meetingSection.meetings

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
import com.baykal.edumyclient.data.model.meeting.response.Meeting
import com.baykal.edumyclient.data.model.user.response.User
import com.baykal.edumyclient.data.model.user.response.UserRole
import com.baykal.edumyclient.ui.component.MeetingCard
import com.baykal.edumyclient.ui.screen.meetingSection.scheduleMeeting.ScheduleMeetingRoute
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetUserInfo


@Composable
fun MeetingsScreen(
    viewModel: MeetingsViewModel
) {
    val context = LocalContext.current
    val viewState by viewModel.uiState.collectAsState()

    with(viewState) {
        LaunchedEffect(meetings) {
            if (classrooms.isNullOrEmpty()) {
                viewModel.getClassrooms()
            }
        }

        Scaffold(
            floatingActionButton = {
                if (user?.role == UserRole.Teacher && meetings != null) {
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
                    scrollState = rememberLazyGridState(),
                    listType = ListType.Grid(spanCount = 2),
                    swipeRefreshSettings = ListSwipeRefreshSettings(enabled = true, onRefresh = viewModel::getMeetings),
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
                            openMeetingActivity(context, user, item)
                        }
                    )
                }
            }
        }
    }
}

fun openMeetingActivity(context: Context, user: User?, meeting: Meeting) {
    val jitsiUser = JitsiMeetUserInfo()
    jitsiUser.displayName = user?.name
    jitsiUser.email = user?.mail

    val options = JitsiMeetConferenceOptions.Builder()
        .setRoom(meeting.id)
        .setSubject(meeting.classroom?.lesson)
        .setUserInfo(jitsiUser)
        .setAudioMuted(false)
        .setVideoMuted(false)
        .setFeatureFlag("pip.enabled", true)
        .setFeatureFlag("add-people.enabled", false)
        .setFeatureFlag("invite.enabled", false)
        .setFeatureFlag("notifications.enabled", false)
        .setFeatureFlag("kick-out.enabled", false)
        .setFeatureFlag("add-people.enabled", false)


    if (user?.role == UserRole.Teacher) {
        options.setFeatureFlag("kick-out.enabled", true)
            .setFeatureFlag("add-people.enabled", true)
    }

    JitsiMeetActivity.launch(context, options.build())
}