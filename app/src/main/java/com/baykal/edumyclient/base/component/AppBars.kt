package com.baykal.edumyclient.base.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.baykal.edumyclient.base.ui.theme.EdumyClientTheme
import com.baykal.edumyclient.base.ui.theme.Gray
import com.baykal.edumyclient.base.ui.theme.Orange
import com.baykal.edumyclient.base.ui.theme.OrangeVariant
import com.baykal.edumyclient.ui.menu.MenuItem
import com.baykal.edumyclient.ui.screen.account.profile.ProfileRoute


@Composable
fun EdumyToolbar(
    navHostController: NavHostController = rememberNavController(),
    title: String = "Toolbar",
    navigateIcon: ImageVector = Icons.Filled.ArrowBack,
    login: Boolean = false,
    topLevelScreen: Set<String> = setOf()
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    TopAppBar(
        backgroundColor = Color.White
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            if (!topLevelScreen.contains(currentRoute)) {
                EIconButton(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .align(Alignment.CenterStart),
                    icon = navigateIcon,
                ) {
                    navHostController.navigateUp()
                }
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                text = title,
                color = Gray
            )
            if (login && !currentRoute.toString().contains("profile")) {
                EIconButton(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .align(Alignment.CenterEnd),
                    icon = Icons.Filled.AccountCircle
                ) {
                    navHostController.navigate(ProfileRoute.route)
                }
            }
        }
    }
}

@Composable
fun EdumyBottomBar(
    navHostController: NavHostController = rememberNavController(),
    items: List<MenuItem> = emptyList(),
) {
    with(navHostController) {
        val navBackStackEntry by currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        BottomNavigation(
            backgroundColor = Color.White,
        ) {
            items.forEach { item ->
                BottomNavigationItem(
                    alwaysShowLabel = currentRoute?.contains(item.route) == true,
                    selected = currentRoute?.contains(item.route) == true,
                    selectedContentColor = Orange,
                    unselectedContentColor = OrangeVariant,
                    icon = { Icon(item.icon, contentDescription = item.title) },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    onClick = {
                        navigate(item.route) {
                            graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun AppBarsPreview() {
    EdumyClientTheme {
        EdumyToolbar()
        EdumyBottomBar()
    }
}