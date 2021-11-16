package com.baykal.edumyclient.ui.screen.meeting

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.baykal.edumyclient.ui.navigation.Screen

@Composable
fun MeetingsScreen(navController: NavController? = null) {
    Button(onClick = { navController?.navigate(Screen.ScheduleMeeting.route) }) {
        Text(text = "Schedule Meeting")
    }
}

@Preview
@Composable
fun MeetingsScreenPreview() {
    MeetingsScreen()
}