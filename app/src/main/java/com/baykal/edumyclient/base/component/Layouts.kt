package com.baykal.edumyclient.base.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baykal.edumyclient.base.ui.theme.Gray
import com.baykal.edumyclient.base.ui.theme.GrayLight
import com.baykal.edumyclient.base.ui.theme.Orange

@Composable
fun ECollapsableLayout(
    collapsedContent: (@Composable () -> Unit)? = null,
    expandedContent: (@Composable () -> Unit)? = null,
    rotatingContent: (@Composable () -> Unit)? = null
) {
    var expanded by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f
    )

    Column(
        modifier = Modifier.animateContentSize(
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearOutSlowInEasing
            )
        )
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                expanded = !expanded
            }
        ) {
            Box(modifier = Modifier.align(Alignment.Center)) {
                collapsedContent?.let {
                    it()
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .rotate(rotationState)
            ) {
                rotatingContent?.let {
                    it()
                }
            }
        }
        if (expanded) {
            expandedContent?.let {
                it()
            }
        }
    }
}

@Composable
fun RowLayout(
    label: String,
    value: String,
    onClick: (() -> Unit)? = null
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .weight(2f),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                text = label
            )
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .weight(4f)
                    .clickable(
                        enabled = onClick != null,
                        onClick = {
                            onClick?.invoke()
                        }
                    ),
                color = if (onClick != null) Orange else Gray,
                fontSize = 14.sp,
                fontWeight = if (onClick != null) FontWeight.Bold else FontWeight.Light,
                text = value
            )
        }
        Divider(
            color = GrayLight,
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
    }
}