package com.baykal.edumyclient.ui.component

import android.widget.DatePicker
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.baykal.edumyclient.R
import java.util.*

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
                EButton(text = "Okay") {
                    onDismiss.invoke()
                }
            }

        }
    }
}