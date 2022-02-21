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
                        val calendar = Calendar.getInstance()
                        calendar.timeInMillis = System.currentTimeMillis()
                        picker.init(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                        ) { _, year, month, day ->
                            onChange("$day.$month.$year")
                        }
                    }
                )
                EButton(text = "Tamam") {
                    onDismiss.invoke()
                }
            }
        }
    }
}