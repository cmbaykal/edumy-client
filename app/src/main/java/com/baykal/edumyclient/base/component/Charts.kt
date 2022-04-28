package com.baykal.edumyclient.base.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.res.dimensionResource
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.extension.fontDimensionResource

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
                    val rect = Rect(Offset(-radius, -radius), Size(2 * radius, 2 * radius))

                    translate(radius, radius) {
                        data.forEach {
                            val angle = it.point / sum * 360f
                            path.moveTo(0f, 0f)
                            path.arcTo(rect = rect, startAngle, angle * animation.value, false)
                            angles.add(angle)
                            drawPath(path = path, color = it.color)
                            path.reset()
                            startAngle += angle
                        }
                    }
                }
                EList(
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_standard)),
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