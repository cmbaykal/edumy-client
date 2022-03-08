package com.baykal.edumyclient.base.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate

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
            Box(modifier = Modifier
                .align(Alignment.CenterEnd)
                .rotate(rotationState)) {
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