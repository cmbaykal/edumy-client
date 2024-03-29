package com.baykal.edumyclient.ui.component

import androidx.compose.animation.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.baykal.edumyclient.base.component.EdumyBottomBar
import com.baykal.edumyclient.base.component.EdumyToolbar
import com.baykal.edumyclient.base.component.GenericDialog
import com.baykal.edumyclient.base.component.LoadingDialog
import com.baykal.edumyclient.base.ui.theme.EdumyClientTheme
import com.baykal.edumyclient.ui.MainState
import com.baykal.edumyclient.ui.menu.MenuItem
import com.baykal.edumyclient.ui.screen.account.login.LoginRoute
import com.baykal.edumyclient.ui.screen.account.profile.ProfileRoute
import com.baykal.edumyclient.ui.screen.account.register.RegisterRoute
import com.baykal.edumyclient.ui.screen.account.update.UpdateUserRoute
import com.baykal.edumyclient.ui.screen.classroomSection.classroomDetail.ClassroomDetailRoute
import com.baykal.edumyclient.ui.screen.classroomSection.classrooms.ClassroomsRoute
import com.baykal.edumyclient.ui.screen.classroomSection.createClass.CreateClassRoute
import com.baykal.edumyclient.ui.screen.meetingSection.meetings.MeetingsRoute
import com.baykal.edumyclient.ui.screen.meetingSection.scheduleMeeting.ScheduleMeetingRoute
import com.baykal.edumyclient.ui.screen.questionSection.answers.AnswersRoute
import com.baykal.edumyclient.ui.screen.questionSection.askquestion.AskQuestionRoute
import com.baykal.edumyclient.ui.screen.questionSection.questionDetail.QuestionDetailRoute
import com.baykal.edumyclient.ui.screen.questionSection.questions.QuestionsRoute
import com.baykal.edumyclient.ui.screen.questionSection.sendAnswer.WriteAnswerRoute
import com.baykal.edumyclient.ui.screen.studiesSection.sendStudy.SendStudyRoute
import com.baykal.edumyclient.ui.screen.studiesSection.studies.StudiesRoute
import com.google.accompanist.insets.ProvideWindowInsets
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun EdumyComponent(state: MutableStateFlow<MainState>) {
    val navController = rememberNavController()
    val mainState by state.collectAsState()

    ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        EdumyClientTheme {
            Scaffold(
                topBar = {
                    AnimatedVisibility(
                        visible = mainState.topBarVisibility,
                        enter = expandVertically(),
                        exit = shrinkVertically()
                    ) {
                        EdumyToolbar(
                            navHostController = navController,
                            title = stringResource(mainState.screenTitle),
                            topLevelScreen = setOf(
                                stringResource(id = ClassroomsRoute.title),
                                stringResource(id = QuestionsRoute.BASE_TITLE),
                                stringResource(id = StudiesRoute.BASE_TITLE),
                                stringResource(id = MeetingsRoute.title)
                            ),
                            menuVisibility = mainState.menuVisibility,
                            menuIcon = Icons.Filled.AccountCircle,
                            menuRoute = ProfileRoute.route
                        )
                    }
                },
                bottomBar = {
                    AnimatedVisibility(
                        visible = mainState.bottomBarVisibility,
                        enter = expandVertically(),
                        exit = shrinkVertically()
                    ) {
                        EdumyBottomBar(
                            navHostController = navController,
                            items = MenuItem.bottomNavigationItems,
                        )
                    }
                }
            ) {
                NavHost(
                    navController = navController,
                    startDestination = mainState.startRoute ?: LoginRoute.route,
                    modifier = Modifier.padding(it)
                ) {
                    LoginRoute.composable(this, navController, state)
                    RegisterRoute.composable(this, navController, state)
                    ProfileRoute.composable(this, navController, state)
                    UpdateUserRoute.composable(this, navController, state)
                    ClassroomsRoute.composable(this, navController, state)
                    CreateClassRoute.composable(this, navController, state)
                    ClassroomDetailRoute.composable(this, navController, state)
                    QuestionsRoute.composable(this, navController, state)
                    AskQuestionRoute.composable(this, navController, state)
                    QuestionDetailRoute.composable(this, navController, state)
                    WriteAnswerRoute.composable(this, navController, state)
                    AnswersRoute.composable(this, navController, state)
                    StudiesRoute.composable(this, navController, state)
                    SendStudyRoute.composable(this, navController, state)
                    MeetingsRoute.composable(this, navController, state)
                    ScheduleMeetingRoute.composable(this, navController, state)
                }
            }
            AnimatedVisibility(
                visible = mainState.dialogState != null,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                mainState.dialogState?.let { dialog ->
                    GenericDialog(
                        title = dialog.title,
                        message = dialog.message,
                        onDismiss = dialog.onDismiss
                    )
                }
            }
            AnimatedVisibility(
                visible = mainState.loadingState,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                LoadingDialog()
            }
        }
    }
}
