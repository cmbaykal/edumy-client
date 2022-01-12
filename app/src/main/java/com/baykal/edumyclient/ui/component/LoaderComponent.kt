package com.baykal.edumyclient.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baykal.edumyclient.base.color

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .background(color = "#40333333".color)
            .fillMaxSize()
            .clickable {  },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(color = Color.White)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Please Wait...",
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun LoadingPreview() {
    LoadingIndicator()
}