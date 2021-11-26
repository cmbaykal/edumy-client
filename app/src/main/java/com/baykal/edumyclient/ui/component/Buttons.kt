package com.baykal.edumyclient.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier
            .padding(8.dp)
            .defaultMinSize(minHeight = 50.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 6.dp,
            pressedElevation = 8.dp,
            disabledElevation = 0.dp
        ),
        onClick = { onClick() }
    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}

@Composable
fun ETextButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    TextButton(
        modifier = modifier
            .padding(8.dp)
            .defaultMinSize(minHeight = 40.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        onClick = { onClick() }
    ) {
        Text(
            text = text,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonsPreview() {
    Column {
        EButton(text = "Edumy Button")
        ETextButton(text = "Edumy Button")
    }
}