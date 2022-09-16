package com.baykal.edumyclient.base.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.res.dimensionResource
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.extension.fontDimensionResource
import kotlin.math.round

data class ChartData(
    val identifier: String,
    val point: Int,
    val color: Color
)

@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    title: String? = null,
    data: List<ChartData>? = null
) {
    val radiusMedium = dimensionResource(id = R.dimen.radius_medium)

    if (!data.isNullOrEmpty()) {
        val sum = data.sumOf { it.point }.toFloat()
        val path = Path()
        val angles = mutableListOf<Float>()

        val animation = remember { Animatable(initialValue = 0f) }

        LaunchedEffect(data) {
            animation.animateTo(
                targetValue = 1f,
                animationSpec = TweenSpec(800)
            )
        }

        BoxWithConstraints(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Canvas(
                    modifier = Modifier
                        .padding(end = dimensionResource(id = R.dimen.padding_standard))
                        .size(this@BoxWithConstraints.maxHeight)
                ) {
                    var startAngle = 0f
                    val radius = this@BoxWithConstraints.maxHeight.toPx() / 2f
                    val size = radius * 2
                    val rect = Rect(Offset(-radius, -radius), Size(size, size))

                    translate(radius, radius) {
                        data.forEachIndexed { index, item ->
                            val ratio = item.point / sum
                            val maxAngle = if (index == data.lastIndex) 360f else 359f
                            val angle = round(ratio * maxAngle)
                            path.moveTo(0f, 0f)
                            path.arcTo(
                                rect = rect,
                                startAngleDegrees = startAngle,
                                sweepAngleDegrees = angle * animation.value, false
                            )
                            angles.add(angle)
                            drawPath(
                                path = path,
                                color = item.color
                            )
                            drawPath(
                                path = path,
                                style = Stroke(width = 8f),
                                color = Color.White
                            )
                            path.reset()
                            startAngle += angle
                        }
                        drawCircle(
                            color = Color.White,
                            center = Offset(0f, 0f),
                            radius = radius / 2
                        )
                    }
                }
                EList(
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_standard)),
                    scrollState = rememberLazyListState(),
                    listItems = data
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Canvas(modifier = Modifier) {
                            drawCircle(it.color, radius = radiusMedium.toPx())
                        }
                        Text(
                            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_standard)),
                            fontSize = fontDimensionResource(id = R.dimen.font_size_small),
                            text = it.identifier
                        )
                    }
                }
            }
        }
    }
}