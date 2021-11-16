package com.baykal.edumyclient.ui.screen.performance.performances

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.baykal.edumyclient.ui.navigation.Screen

@Composable
fun PerformancesScreen(navController: NavController? = null) {
    Button(onClick = { navController?.navigate(Screen.AddPerformance.route) }) {
        Text(text = "Add Performance")
    }
}

@Preview
@Composable
fun PerformancesScreenPreview() {
    PerformancesScreen()
}