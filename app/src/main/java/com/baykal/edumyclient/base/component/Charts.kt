package com.baykal.edumyclient.base.component

import android.graphics.Point
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.baykal.edumyclient.base.ui.theme.Gray
import com.baykal.edumyclient.base.ui.theme.Orange

@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    xText: String = "",
    yText: String = "",
) {
    val points = listOf(
        Point(10, 10),
        Point(90, 100),
        Point(170, 30),
        Point(250, 200),
        Point(330, 120),
    )

    val animation = remember { Animatable(initialValue = 0f)}

    LaunchedEffect(points){
        animation.animateTo(
            targetValue = 1f,
            animationSpec = FloatTweenSpec(800)
        )
    }

    BoxWithConstraints(
        modifier = modifier
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            drawLine(
                start = Offset(0f, 0f),
                end = Offset(0f, maxHeight.toPx()),
                color = Gray,
                strokeWidth = 3f
            )
            drawLine(
                start = Offset(0f, maxHeight.toPx()),
                end = Offset(maxWidth.toPx(), maxHeight.toPx()),
                color = Gray,
                strokeWidth = 3f
            )

            points.forEachIndexed { i, p ->
                drawRoundRect(
                    cornerRadius = CornerRadius(20f, 20f),
                    color = Orange,
                    topLeft = Offset(
                        10.dp.toPx() + (50.dp.toPx() * i),
                        maxHeight.toPx() - (maxHeight.toPx()- (10.dp.toPx() * i)) * animation.value
                    ),
                    size = Size(40.dp.toPx(), (maxHeight.toPx() - (10.dp.toPx() * i)) * animation.value)
                )
            }
        }
    }
//    points.forEach { p ->
//        Column {
//            Text(
//                text = "test",
//                fontSize = 8.sp
//            )
//            Canvas(modifier = Modifier) {
//                drawRoundRect(
//                    cornerRadius = CornerRadius(20f, 20f),
//                    color = Orange,
//                    topLeft = Offset(p.x + 30f, 600 - (600 - p.y) * heightPre),
//                    size = Size(60f, (600 - p.y) * heightPre)
//                )
//            }
//        }
//    }
}