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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.extension.fontDimensionResource
import com.baykal.edumyclient.base.ui.theme.EdumyClientTheme
import com.baykal.edumyclient.base.ui.theme.Gray
import com.baykal.edumyclient.base.ui.theme.Orange

@Composable
fun EButton(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(dimensionResource(id = R.dimen.radius_standard)),
    text: String,
    textColor: Color = Color.White,
    textSize: TextUnit = TextUnit.Unspecified,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier.heightIn(0.dp, dimensionResource(id = R.dimen.button_height_standard)),
        shape = shape,
        elevation = ButtonDefaults.elevation(
            defaultElevation = dimensionResource(id = R.dimen.elevation_standard),
            pressedElevation = dimensionResource(id = R.dimen.elevation_big),
            disabledElevation = 0.dp
        ),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_standard)),
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
    size: Dp = dimensionResource(id = R.dimen.button_height_standard),
    icon: ImageVector,
    iconColor: Color = Gray,
    iconSize: Dp = dimensionResource(id = R.dimen.icon_size_standard),
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
            .padding(dimensionResource(id = R.dimen.padding_standard))
            .defaultMinSize(minHeight = dimensionResource(id = R.dimen.button_height_standard))
            .then(modifier),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.radius_standard)),
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
                start = dimensionResource(id = R.dimen.padding_big),
                end = dimensionResource(id = R.dimen.padding_big),
                top = dimensionResource(id = R.dimen.padding_standard)
            )
            .then(modifier),
        elevation = dimensionResource(id = R.dimen.elevation_standard)
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
                            .padding(start = dimensionResource(id = R.dimen.padding_standard))
                            .size(dimensionResource(id = R.dimen.icon_size_medium)),
                        imageVector = icon,
                        tint = iconColor,
                        contentDescription = ""
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = dimensionResource(id = R.dimen.padding_standard),
                            end = dimensionResource(id = R.dimen.padding_big),
                            top = dimensionResource(id = R.dimen.padding_big),
                            bottom = dimensionResource(id = R.dimen.padding_big)
                        ),
                    text = text,
                    textAlign = TextAlign.Start,
                    fontSize = fontDimensionResource(id = R.dimen.font_size_standard),
                    fontWeight = FontWeight.Bold
                )
            }
            Icon(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.arrow_icon_size))
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