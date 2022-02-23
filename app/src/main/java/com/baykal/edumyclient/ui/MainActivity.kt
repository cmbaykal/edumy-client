package com.baykal.edumyclient.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.baykal.edumyclient.base.component.EdumyBottomBar
import com.baykal.edumyclient.base.component.EdumyToolbar
import com.baykal.edumyclient.base.menu.MenuItem
import com.baykal.edumyclient.base.nav.NavigationComponent
import com.baykal.edumyclient.base.theme.EdumyClientTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EdumyClientTheme {
                Surface(color = MaterialTheme.colors.background) {
                    EdumyClient()
                }
            }
        }
    }

    @Composable
    fun EdumyClient() {
        val navController = rememberNavController()
        val mainState = remember { mutableStateOf(MainState()) }
        Scaffold(
            topBar = {
                EdumyToolbar(
                    title = mainState.value.title,
                    visibility = mainState.value.topBarVisibility,
                    navigateUp = { navController.navigateUp() }
                )
            },
            bottomBar = {
                val items = listOf(
                    MenuItem.Classrooms,
                    MenuItem.Questions,
                    MenuItem.Performances,
                    MenuItem.Usages
                )
                EdumyBottomBar(
                    navHostController = navController,
                    items = items,
                    visibility = mainState.value.bottomBarVisibility
                )
            }
        ) {
            NavigationComponent(navController, mainState, it)
        }
    }
}


