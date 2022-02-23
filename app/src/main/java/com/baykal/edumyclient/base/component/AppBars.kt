package com.baykal.edumyclient.base.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.baykal.edumyclient.base.menu.MenuItem
import com.baykal.edumyclient.base.theme.Orange
import com.baykal.edumyclient.base.theme.OrangeVariant


@Composable
fun EdumyToolbar(
    title: String = "Toolbar",
    navigateIcon: ImageVector = Icons.Filled.ArrowBack,
    navigateUp: (() -> Unit)? = null,
    visibility: Boolean = false
) {
    if (visibility) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .padding(bottom = 4.dp)
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp),
                    clip = true
                )
                .background(Color.White)
                .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)),
        ) {
            navigateUp?.let {
                Box(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(40.dp)
                        .align(Alignment.CenterStart)
                        .clickable(
                            onClick = { it.invoke() },
                            indication = rememberRipple(color = OrangeVariant, radius = 18.dp),
                            interactionSource = remember { MutableInteractionSource() },
                        )
                ) {
                    Icon(
                        navigateIcon,
                        contentDescription = "Navigate",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                text = title
            )
        }
    }
}

@Composable
fun EdumyBottomBar(
    navHostController: NavHostController = rememberNavController(),
    items: List<MenuItem> = emptyList(),
    visibility: Boolean = false
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (visibility) {
        BottomNavigation(
            backgroundColor = Color.White,
        ) {
            items.forEach { item ->
                BottomNavigationItem(
                    alwaysShowLabel = currentRoute == item.route,
                    selected = currentRoute == item.route,
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
                        navHostController.navigate(item.route) {
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
fun EdumyBottomBarPreview() {
    EdumyBottomBar()
}

@Preview
@Composable
fun EdumyToolbarPreview() {
    EdumyToolbar()
}