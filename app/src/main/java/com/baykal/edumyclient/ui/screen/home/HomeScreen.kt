package com.baykal.edumyclient.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHost
import com.baykal.edumyclient.R
import com.baykal.edumyclient.ui.navigation.Screen
import com.baykal.edumyclient.ui.screen.account.profile.ProfileScreen
import com.baykal.edumyclient.ui.screen.appUsage.AppUsageScreen
import com.baykal.edumyclient.ui.screen.askQuestion.askquestion.AskQuestionScreen
import com.baykal.edumyclient.ui.screen.askQuestion.questionDetail.QuestionDetailScreen
import com.baykal.edumyclient.ui.screen.askQuestion.questions.QuestionsScreen
import com.baykal.edumyclient.ui.screen.classroom.classrooms.ClassRoomsScreen
import com.baykal.edumyclient.ui.screen.classroom.createClass.CreateClassScreen
import com.baykal.edumyclient.ui.screen.meeting.MeetingsScreen
import com.baykal.edumyclient.ui.screen.meeting.scheduleMeeting.ScheduleMeetingScreen
import com.baykal.edumyclient.ui.screen.performance.addPerformance.AddPerformanceScreen
import com.baykal.edumyclient.ui.screen.performance.performances.PerformancesScreen
import com.baykal.edumyclient.ui.theme.Gray
import com.baykal.edumyclient.ui.theme.Orange

@Composable
fun HomeScreen() {

//    val navController = rememberNavController()
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//    val currentRoute = currentDestination?.route ?: Screen.Home.route
//
//    val items = listOf(
//        Screen.Classrooms,
//        Screen.Performances,
//        Screen.Questions,
//        Screen.Meetings,
//        Screen.AppUsages,
//    )
//
//    Scaffold(
//        topBar = {
//            ConstraintLayout(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(60.dp),
//            ) {
//                val (back, title, profile) = createRefs()
//                if (!items.contains(Screen.withRoute(currentRoute))) {
//                    IconButton(
//                        modifier = Modifier
//                            .padding(start = 12.dp)
//                            .size(30.dp)
//                            .constrainAs(back) {
//                                start.linkTo(parent.start)
//                                top.linkTo(parent.top)
//                                bottom.linkTo(parent.bottom)
//                            },
//                        onClick = {
//                            navController.navigateUp()
//                        }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            tint = Gray,
//                            contentDescription = ""
//                        )
//                    }
//                }
//                Text(
//                    modifier = Modifier.constrainAs(title) {
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                        top.linkTo(parent.top)
//                        bottom.linkTo(parent.bottom)
//                    },
//                    fontSize = 22.sp,
//                    fontWeight = FontWeight.ExtraBold,
//                    textAlign = TextAlign.Center,
//                    text = stringResource(id = Screen.withRoute(currentRoute).title)
//                )
//                Card(
//                    shape = RoundedCornerShape(8.dp),
//                    elevation = 4.dp,
//                    modifier = Modifier
//                        .padding(end = 12.dp)
//                        .size(35.dp)
//                        .constrainAs(profile) {
//                            end.linkTo(parent.end)
//                            top.linkTo(parent.top)
//                            bottom.linkTo(parent.bottom)
//                        },
//                ) {
//                    Image(
//                        painterResource(R.drawable.test),
//                        contentDescription = "",
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .clickable {
//                                navController.navigate(Screen.Profile.route)
//                            }
//                    )
//                }
//            }
//        },
//        bottomBar = {
//            BottomNavigation(
//                modifier = Modifier
//                    .padding(12.dp)
//                    .graphicsLayer {
//                        shape = RoundedCornerShape(10.dp)
//                        shadowElevation = 20f
//                    }
//                    .clip(RoundedCornerShape(10.dp)),
//                backgroundColor = Gray,
//                contentColor = Orange
//            ) {
//                items.forEach { screen ->
//                    BottomNavigationItem(
//                        icon = { Icon(screen.icon, contentDescription = null) },
//                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
//                        onClick = {
//                            navController.navigate(screen.route) {
//                                popUpTo(navController.graph.findStartDestination().id) {
//                                    saveState = true
//                                }
//                                launchSingleTop = true
//                                restoreState = true
//                            }
//                        }
//                    )
//                }
//            }
//        }
//    ) { innerPadding ->
//        NavHost(
//            navController,
//            startDestination = Screen.Classrooms.route,
//            modifier = Modifier.padding(innerPadding)
//        ) {
//            composable(Screen.Classrooms.route) { ClassRoomsScreen(navController) }
//            composable(Screen.Performances.route) { PerformancesScreen(navController) }
//            composable(Screen.Questions.route) { QuestionsScreen(navController) }
//            composable(Screen.Meetings.route) { MeetingsScreen(navController) }
//            composable(Screen.AppUsages.route) { AppUsageScreen() }
//            composable(Screen.Profile.route) { ProfileScreen() }
//            composable(Screen.CreateClass.route) { CreateClassScreen() }
//            composable(Screen.AddPerformance.route) { AddPerformanceScreen() }
//            composable(Screen.AskQuestion.route) { AskQuestionScreen() }
//            composable(Screen.QuestionDetail.route) { QuestionDetailScreen() }
//            composable(Screen.ScheduleMeeting.route) { ScheduleMeetingScreen() }
//        }
//    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}