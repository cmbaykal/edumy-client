package com.baykal.edumyclient.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baykal.edumyclient.base.color
import com.baykal.edumyclient.ui.theme.Orange
import com.baykal.edumyclient.ui.theme.OrangeVariant

@Composable
fun ETextField(
    label: String,
    onChange: (InputState) -> Unit,
    success: ((text: String) -> Boolean)? = null,
    errorText: String? = null
) {
    var focused by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    val colors = TextFieldDefaults.textFieldColors(
        textColor = Color.Black,
        cursorColor = Color.Black,
        focusedLabelColor = Orange,
        unfocusedLabelColor = Color.Gray,
        errorLabelColor = "#ff6d64".color,
        focusedIndicatorColor = Orange,
        unfocusedIndicatorColor = OrangeVariant,
        errorIndicatorColor = "#ff6d64".color,
        errorTrailingIconColor = "#ff6d64".color
    )

    Column(
        Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focused = it.isFocused },
            colors = colors,
            value = text,
            onValueChange = {
                text = it
                isError = success != null && !success(it) && it.isNotEmpty()
                onChange(InputState(it, isError))
            },
            label = {
                Text(
                    text = if (focused || text.isNotEmpty()) label.uppercase() else label,
                    fontWeight = if (focused || text.isNotEmpty()) FontWeight.Bold else FontWeight.Normal
                )
            },
            trailingIcon = {
                if (text.isNotEmpty() && !focused && success != null) {
                    Icon(
                        imageVector = if (isError) Icons.Default.Close else Icons.Default.Check,
                        tint = if (isError) "#ff6d64".color else "#66c95b".color,
                        contentDescription = ""
                    )
                }
            },
            isError = isError && !focused,
        )
        if (isError && !focused && !errorText.isNullOrEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 16.dp),
                fontSize = 12.sp,
                textAlign = TextAlign.Start,
                text = errorText,
                color = "#ff6d64".color
            )
        }
    }
}

data class InputState(
    var text: String = "",
    var isError: Boolean = true
)

@Preview(showBackground = true)
@Composable
fun TextFieldsPreview() {
    ETextField(
        label = "Edumy Text Field",
        onChange = { },
        success = { it.isEmpty() },
        errorText = "Cannot be empty"
    )
}