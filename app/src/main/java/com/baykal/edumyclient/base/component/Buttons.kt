package com.baykal.edumyclient.base.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baykal.edumyclient.base.ui.theme.EdumyClientTheme
import com.baykal.edumyclient.base.ui.theme.Gray
import com.baykal.edumyclient.base.ui.theme.Orange

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
        Text(text = text)
    }
}

@Composable
fun EFab(
    onClick: () -> Unit,
    color: Color = Orange,
    iconColor: Color = Gray,
    icon: ImageVector = Icons.Filled.Add
) {
    FloatingActionButton(
        onClick = { onClick.invoke() },
        backgroundColor = color,
        content = {
            Icon(
                imageVector = icon,
                tint = iconColor,
                contentDescription = ""
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ButtonsPreview() {
    EdumyClientTheme {
        Column {
            EButton(text = "Edumy Button")
            ETextButton(text = "Edumy Button")
            EFab(onClick = { })
        }
    }
}