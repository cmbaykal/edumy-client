package com.baykal.edumyclient.base.component

import android.widget.DatePicker
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.baykal.edumyclient.R
import java.text.DecimalFormat
import java.util.*

@Composable
fun GenericDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit = {}
) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            modifier = Modifier.clip(RoundedCornerShape(10.dp)),
            onDismissRequest = onDismiss,
            title = { Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp) },
            text = { Text(message, fontSize = 14.sp) },
            confirmButton = {
                ETextButton(text = "Tamam") {
                    showDialog = false
                    onDismiss.invoke()
                }
            }
        )
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