package com.baykal.edumyclient.base.component

import android.widget.DatePicker
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.baykal.edumyclient.R
import java.text.DecimalFormat
import java.util.*

data class DialogButton(
    val label: String,
    val onClick: () -> Unit
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
                    modifier = Modifier.fillMaxWidth(),
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
                            onDismiss.invoke()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EDatePicker(
    onChange: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val decimalFormat = DecimalFormat("00")
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = System.currentTimeMillis()
    val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
    val currentMonth = calendar.get(Calendar.MONTH)
    val currentYear = calendar.get(Calendar.YEAR)

    onChange("${decimalFormat.format(currentDay)}.${decimalFormat.format(currentMonth)}.$currentYear")

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
                            currentYear,
                            currentMonth,
                            currentDay
                        ) { _, year, month, day ->
                            val dayText = decimalFormat.format(day)
                            val monthText = decimalFormat.format(month)
                            onChange("$dayText.$monthText.$year")
                        }
                    }
                )
                EButton(text = "Okay") {
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