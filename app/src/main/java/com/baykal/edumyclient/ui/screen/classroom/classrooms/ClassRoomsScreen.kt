package com.baykal.edumyclient.ui.screen.classroom.classrooms

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.baykal.edumyclient.ui.navigation.Screen

@Composable
fun ClassRoomsScreen(navController: NavController? = null) {

    val viewModel: ClassRoomViewModel = hiltViewModel()

    Button(onClick = { navController?.navigate(Screen.CreateClass.route) }) {
        Text(text = "Create Class")
    }
}

@Preview
@Composable
fun ClassRoomsScreenPreview() {
    ClassRoomsScreen()
}