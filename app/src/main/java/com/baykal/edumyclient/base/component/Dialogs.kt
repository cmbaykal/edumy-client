@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED")

package com.baykal.edumyclient.base.component

import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.extension.fontDimensionResource
import com.baykal.edumyclient.di.BASE_URL
import java.util.*

data class DialogButton(
    val label: String,
    val onClick: () -> Unit = {}
)

@Composable
fun GenericDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit = {}
) {
    AlertDialog(
        modifier = Modifier.clip(RoundedCornerShape(dimensionResource(id = R.dimen.radius_standard))),
        onDismissRequest = onDismiss,
        title = { Text(title, fontWeight = FontWeight.Bold, fontSize = fontDimensionResource(id = R.dimen.font_size_big)) },
        text = { Text(message, fontSize = fontDimensionResource(id = R.dimen.font_size_medium)) },
        confirmButton = {
            ETextButton(text = stringResource(id = R.string.okay_button)) {
                onDismiss.invoke()
            }
        }
    )
}

@Composable
fun EDialog(
    title: String,
    cancellable: Boolean = true,
    onDismiss: () -> Unit = {},
    positiveButton: DialogButton? = null,
    negativeButton: DialogButton? = null,
    content: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = cancellable,
            dismissOnClickOutside = cancellable,
        )
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.radius_standard)))
                .background(Color.White),
        ) {
            Column {
                Text(
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.padding_big),
                        top = dimensionResource(id = R.dimen.padding_big)
                    ),
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = fontDimensionResource(id = R.dimen.font_size_big)
                )
                content()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = dimensionResource(id = R.dimen.padding_standard)),
                    horizontalArrangement = Arrangement.End
                ) {
                    negativeButton?.let {
                        ETextButton(text = it.label) {
                            it.onClick()
                            onDismiss.invoke()
                        }
                    }
                    positiveButton?.let {
                        ETextButton(text = it.label) {
                            it.onClick()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EDatePicker(
    onChange: (Date) -> Unit,
    timeEnabled: Boolean = false
) {
    var firstState by remember { mutableStateOf(true) }
    val animationTime = 300

    var date by remember { mutableStateOf(Date()) }
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = date.time

    onChange(date)

    BoxWithConstraints {
        AnimatedVisibility(
            visible = firstState,
            exit = slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(
                    durationMillis = animationTime,
                    easing = LinearEasing
                )
            )
        ) {
            AndroidView(
                factory = { DatePicker(ContextThemeWrapper(it, R.style.PickerDialogStyle)) },
                modifier = Modifier.wrapContentSize(),
                update = { picker ->
                    picker.init(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ) { _, year, month, day ->
                        calendar.set(Calendar.DAY_OF_MONTH, day)
                        calendar.set(Calendar.MONTH, month)
                        calendar.set(Calendar.YEAR, year)
                        if (timeEnabled) {
                            firstState = false
                        } else {
                            date = Date(calendar.timeInMillis)
                            onChange(date)
                        }
                    }
                }
            )
        }

        AnimatedVisibility(
            visible = !firstState,
            enter = slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(
                    durationMillis = animationTime,
                    easing = LinearEasing
                )
            ),
        ) {
            AndroidView(
                factory = { TimePicker(ContextThemeWrapper(it, R.style.PickerDialogStyle)) },
                modifier = Modifier.wrapContentSize(),
                update = { picker ->
                    picker.setIs24HourView(true)
                    picker.hour = calendar.get(Calendar.HOUR_OF_DAY)
                    picker.minute = calendar.get(Calendar.MINUTE)
                    picker.setOnTimeChangedListener { _, hourOfDay, minute ->
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        calendar.set(Calendar.MINUTE, minute)
                        date = Date(calendar.timeInMillis)
                        onChange(date)
                    }
                }
            )
        }
    }
}

@Composable
fun LoadingDialog() {
    Dialog(
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        onDismissRequest = {}
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.radius_standard)))
                .background(color = Color.White)
                .padding(dimensionResource(id = R.dimen.padding_big)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
            Text(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_standard)),
                text = stringResource(id = R.string.please_wait_text),
                color = Color.Black
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ImageDialog(
    file: String,
    description: String,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var rotationState by remember { mutableStateOf(1f) }

    Dialog(
        properties = DialogProperties(
            dismissOnClickOutside = false
        ),
        onDismissRequest = onDismiss
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, rotation ->
                        offset += pan
                        scale *= zoom
                        rotationState += rotation
                    }
                }
        ) {
            EIconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                icon = Icons.Default.Close,
                iconColor = Color.White
            ) {
                onDismiss.invoke()
            }
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data("$BASE_URL/image/$file")
                    .crossfade(true)
                    .build(),
                contentDescription = description,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .defaultMinSize(minWidth = dimensionResource(id = R.dimen.image_width_standard))
                    .align(Alignment.Center)
                    .graphicsLayer(
                        scaleX = maxOf(1f, minOf(2f, scale)),
                        scaleY = maxOf(1f, minOf(2f, scale)),
                        translationX = if (scale > 1f) offset.x else 0f,
                        translationY = if (scale > 1f) offset.y else 0f,
                        rotationZ = rotationState
                    )
            )
        }
    }
}