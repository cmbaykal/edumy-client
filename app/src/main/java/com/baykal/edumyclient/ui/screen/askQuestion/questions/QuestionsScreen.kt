package com.baykal.edumyclient.ui.screen.askQuestion.questions

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.baykal.edumyclient.ui.navigation.Screen

@Composable
fun QuestionsScreen(navController: NavController? = null) {
    Column {
        Button(onClick = { navController?.navigate(Screen.QuestionDetail.route) }) {
            Text(text = "Question Detail")
        }
        Button(onClick = { navController?.navigate(Screen.AskQuestion.route) }) {
            Text(text = "Ask Question")
        }
    }
}

@Preview
@Composable
fun QuestionsScreenPreview() {
    QuestionsScreen()
}