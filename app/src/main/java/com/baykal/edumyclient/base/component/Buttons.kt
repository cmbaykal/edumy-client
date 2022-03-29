package com.baykal.edumyclient.base.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baykal.edumyclient.base.ui.theme.EdumyClientTheme
import com.baykal.edumyclient.base.ui.theme.Gray
import com.baykal.edumyclient.base.ui.theme.Orange

@Composable
fun EButton(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(10.dp),
    text: String,
    textColor: Color = Color.White,
    textSize: TextUnit = TextUnit.Unspecified,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier.heightIn(0.dp, 40.dp),
        shape = shape,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 6.dp,
            pressedElevation = 8.dp,
            disabledElevation = 0.dp
        ),
        contentPadding = PaddingValues(8.dp),
        onClick = onClick
    ) {
        Text(
            text = text,
            fontSize = textSize,
            color = textColor
        )
    }
}

@Composable
fun EIconButton(
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    icon: ImageVector,
    iconColor: Color = Gray,
    iconSize: Dp = 24.dp,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .then(modifier)
            .size(size)
            .clickable(
                enabled = enabled,
                onClick = onClick,
                indication = rememberRipple(color = Orange, radius = (size / 2) - 2.dp),
                interactionSource = remember { MutableInteractionSource() },
            )
    ) {
        Icon(
            icon,
            tint = iconColor,
            contentDescription = "Icon Button",
            modifier = Modifier
                .size(iconSize)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun ETextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {}
) {
    TextButton(
        modifier = Modifier
            .padding(8.dp)
            .defaultMinSize(minHeight = 40.dp)
            .then(modifier),
        shape = RoundedCornerShape(10.dp),
        onClick = onClick
    ) {
        Text(text = text)
    }
}

@Composable
fun EFab(
    color: Color = Orange,
    iconColor: Color = Gray,
    icon: ImageVector = Icons.Filled.Add,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
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
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector? = null,
    iconColor: Color = Gray,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 10.dp
            )
            .then(modifier),
        elevation = 4.dp
    ) {
        Box(
            modifier = Modifier.clickable(onClick = onClick)
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