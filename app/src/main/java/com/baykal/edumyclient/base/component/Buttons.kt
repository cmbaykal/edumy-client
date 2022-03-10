package com.baykal.edumyclient.base.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baykal.edumyclient.base.ui.theme.EdumyClientTheme
import com.baykal.edumyclient.base.ui.theme.Gray
import com.baykal.edumyclient.base.ui.theme.Orange

@Composable
fun EButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.White,
    textSize: TextUnit = TextUnit.Unspecified,
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
            fontSize = textSize,
            color = textColor
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

@Composable
fun ScreenButton(
    text: String,
    icon: ImageVector? = null,
    iconColor: Color = Gray,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp),
        elevation = 4.dp
    ) {
        Box(
            modifier = Modifier.clickable {
                onClick.invoke()
            }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                icon?.let {
                    Icon(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(20.dp),
                        imageVector = icon,
                        tint = iconColor,
                        contentDescription = ""
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 10.dp,
                            end = 20.dp,
                            top = 15.dp,
                            bottom = 15.dp,
                        ),
                    text = text,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterEnd),
                imageVector = Icons.Filled.KeyboardArrowRight,
                tint = Gray,
                contentDescription = ""
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonsPreview() {
    EdumyClientTheme {
        Column {
            EButton(text = "Edumy Button")
            ETextButton(text = "Edumy Button")
            EFab(onClick = { })
            ScreenButton(text = "Screen Button", onClick = {})
        }
    }
}