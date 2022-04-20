package com.baykal.edumyclient.ui.screen.questionSection.questions

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.nav.NavRoute

object QuestionsRoute : NavRoute<QuestionsViewModel> {
    const val USER_ID = "userId"
    const val CLASS_ID = "classId"
    const val BASE_TITLE = R.string.questions_screen

    override var title = BASE_TITLE
    override var route = "questions?userId={${USER_ID}}&classId={$CLASS_ID}"

    fun feedQuestions(): String {
        title = BASE_TITLE
        return route
    }

    fun userQuestions(userId: String): String {
        title = R.string.user_questions_screen
        return route.replace("{${USER_ID}}", userId)
    }

    fun classQuestions(classId: String): String {
        title = R.string.classroom_questions_screen
        return route.replace("{${CLASS_ID}}", classId)
    }

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(USER_ID) {
            nullable = true
            defaultValue = null
            type = NavType.StringType
        },
        navArgument(CLASS_ID) {
            nullable = true
            defaultValue = null
            type = NavType.StringType
        }
    )

    override fun bottomBarVisibility() = true
    override fun topBarVisibility() = true

    @Composable
    override fun Content(viewModel: QuestionsViewModel) {
        QuestionsScreen(viewModel)
    }

    @Composable
    override fun viewModel(): QuestionsViewModel = hiltViewModel()
}