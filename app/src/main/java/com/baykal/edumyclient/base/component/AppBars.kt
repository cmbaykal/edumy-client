package com.baykal.edumyclient.base.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.ui.theme.EdumyClientTheme
import com.baykal.edumyclient.base.ui.theme.Gray
import com.baykal.edumyclient.base.ui.theme.Orange
import com.baykal.edumyclient.base.ui.theme.OrangeVariant
import com.baykal.edumyclient.ui.menu.MenuItem


@Composable
fun EdumyToolbar(
    navHostController: NavHostController = rememberNavController(),
    title: String = stringResource(id = R.string.default_toolbar_title),
    navigateIcon: ImageVector = Icons.Filled.ArrowBack,
    topLevelScreen: Set<String> = setOf(),
    menuIcon: ImageVector? = null,
    menuRoute: String? = null
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentLabel = navBackStackEntry?.destination?.label

    TopAppBar(
        backgroundColor = Color.White
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            if (!topLevelScreen.contains(currentLabel)) {
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
            if (menuIcon != null && currentRoute?.contains(menuRoute.toString()) == false)
                EIconButton(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .align(Alignment.CenterEnd),
                    icon = menuIcon
                ) {
                    menuRoute?.let {
                        navHostController.navigate(it)
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
        val currentLabel = navBackStackEntry?.destination?.label
        BottomNavigation(
            backgroundColor = Color.White,
        ) {
            items.forEach { item ->
                val title = stringResource(id = item.title)
                val selected = currentLabel?.equals(title) == true

                BottomNavigationItem(
                    alwaysShowLabel = selected,
                    selected = selected,
                    selectedContentColor = Orange,
                    unselectedContentColor = OrangeVariant,
                    icon = { Icon(item.icon, contentDescription = title) },
                    label = {
                        Text(
                            text = title,
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