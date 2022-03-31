package com.baykal.edumyclient.base.component

import android.util.Log
import android.widget.DatePicker
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.baykal.edumyclient.R
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
        modifier = Modifier.clip(RoundedCornerShape(10.dp)),
        onDismissRequest = onDismiss,
        title = { Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp) },
        text = { Text(message, fontSize = 14.sp) },
        confirmButton = {
            ETextButton(text = "Tamam") {
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
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White),
        ) {
            Column {
                Text(
                    modifier = Modifier.padding(
                        start = 18.dp,
                        top = 15.dp
                    ),
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                content()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
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
    date: Date = Date(),
    onChange: (Date) -> Unit,
    onDismiss: () -> Unit
) {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = date.time

    onChange(date)

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(10.dp)
        ) {
            Column {
                AndroidView(
                    {
                        DatePicker(ContextThemeWrapper(it, R.style.DatePicker))
                    },
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
                            onChange(Date(calendar.timeInMillis))
                        }
                    }
                )
                EButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    text = "Okay"
                ) {
                    onDismiss.invoke()
                }
            }
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
                .clip(RoundedCornerShape(10.dp))
                .background(color = Color.White)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Please Wait...",
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
                        Log.d("EdumyTest", "Offset :$offset")
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
                    .defaultMinSize(minWidth = 200.dp)
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