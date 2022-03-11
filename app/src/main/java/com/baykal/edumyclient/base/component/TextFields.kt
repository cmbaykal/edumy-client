package com.baykal.edumyclient.base.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baykal.edumyclient.base.extension.color
import com.baykal.edumyclient.base.ui.theme.EdumyClientTheme
import com.baykal.edumyclient.base.ui.theme.Gray
import com.baykal.edumyclient.base.ui.theme.Orange
import com.baykal.edumyclient.base.ui.theme.OrangeVariant

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ETextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String = "",
    errorText: String? = null,
    onChange: (InputState) -> Unit,
    success: ((text: String) -> Boolean) = { true },
    imeAction: ImeAction? = null,
    onAction: (() -> Unit)? = null,
    passwordToggle: Boolean = false
) {
    var text by remember { mutableStateOf(value) }
    var focused by remember { mutableStateOf(false) }
    var isSuccess by remember { mutableStateOf(success.invoke(value)) }
    var charVisibility by remember { mutableStateOf(!passwordToggle) }
    val keyboardController = LocalSoftwareKeyboardController.current

    val colors = TextFieldDefaults.textFieldColors(
        focusedLabelColor = Orange,
        unfocusedLabelColor = Color.Gray,
        errorLabelColor = "#ff6d64".color,
        focusedIndicatorColor = Orange,
        unfocusedIndicatorColor = OrangeVariant,
        errorIndicatorColor = "#ff6d64".color,
        errorTrailingIconColor = "#ff6d64".color
    )

    onChange.invoke(InputState(text, isSuccess))

    Column(
        Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focused = it.isFocused }
                .then(modifier),
            colors = colors,
            value = text,
            onValueChange = {
                text = it
                isSuccess = success.invoke(it)
                onChange.invoke(InputState(it, isSuccess))
            },
            label = {
                Text(
                    text = if (focused || text.isNotEmpty()) label.uppercase() else label,
                    fontWeight = if (focused || text.isNotEmpty()) FontWeight.Bold else FontWeight.Normal
                )
            },
            visualTransformation = if (charVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (text.isNotEmpty() && !focused) {
                        Icon(
                            imageVector = if (isSuccess) Icons.Default.Check else Icons.Default.Close,
                            tint = if (isSuccess) "#66c95b".color else "#ff6d64".color,
                            contentDescription = ""
                        )
                    }
                    if (passwordToggle && focused) {
                        IconButton(
                            modifier = Modifier.size(30.dp),
                            onClick = {
                                charVisibility = !charVisibility
                            }) {
                            Icon(
                                imageVector = if (charVisibility) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                tint = Orange,
                                contentDescription = ""
                            )
                        }
                    }
                }
            },
            isError = !isSuccess && !focused && text.isNotEmpty(),
            keyboardOptions = KeyboardOptions(imeAction = imeAction ?: ImeAction.None),
            keyboardActions = onAction?.let {
                KeyboardActions {
                    it.invoke()
                    keyboardController?.hide()
                }
            } ?: run {
                KeyboardActions.Default
            }
        )
        if (!isSuccess && !focused && !errorText.isNullOrEmpty()) {
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ESearchView(
    modifier: Modifier = Modifier,
    label: String,
    value: String = "",
    onChange: (String) -> Unit,
    onAction: (() -> Unit)? = null,
) {
    var text by remember { mutableStateOf(value) }
    var focused by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    val colors = TextFieldDefaults.textFieldColors(
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
            modifier = modifier
                .then(
                    Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focused = it.isFocused }
                ),
            colors = colors,
            shape = RoundedCornerShape(50.dp),
            value = text,
            onValueChange = {
                text = it
                onChange.invoke(it)
            },
            placeholder = {
                Text(text = label)
            },
            trailingIcon = {
                Row(
                    modifier = Modifier.padding(end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(40.dp)
                            .clickable(
                                onClick = {
                                    if (text.isNotEmpty()) {
                                        text = ""
                                        onChange.invoke(text)
                                    }
                                },
                                indication = rememberRipple(color = OrangeVariant, radius = 18.dp),
                                interactionSource = remember { MutableInteractionSource() },
                            )
                    ) {
                        Icon(
                            if (text.isEmpty()) Icons.Filled.Search else Icons.Filled.Close,
                            tint = if (focused) Orange else Gray,
                            contentDescription = "",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = onAction?.let {
                KeyboardActions {
                    it.invoke()
                    keyboardController?.hide()
                }
            } ?: run {
                KeyboardActions.Default
            }
        )
    }
}

@Composable
fun EDateField(
    label: String,
    onChange: (InputState) -> Unit,
    onDismiss: (() -> Unit) = { }
) {
    var dialogState by remember { mutableStateOf(false) }
    var focused by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    val colors = TextFieldDefaults.textFieldColors(
        focusedLabelColor = Orange,
        unfocusedLabelColor = Color.Gray,
        errorLabelColor = "#ff6d64".color,
        focusedIndicatorColor = Orange,
        unfocusedIndicatorColor = OrangeVariant,
        errorIndicatorColor = "#ff6d64".color,
        errorTrailingIconColor = "#ff6d64".color
    )

    Box(
        Modifier
            .padding(8.dp)
            .clickable {
                dialogState = true
            },
    ) {
        OutlinedTextField(
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    focused = it.isFocused
                    if (it.isFocused) {
                        dialogState = true
                    }
                },
            colors = colors,
            value = text,
            onValueChange = { text = it },
            label = {
                Text(
                    text = if (focused || text.isNotEmpty()) label.uppercase() else label,
                    fontWeight = if (focused || text.isNotEmpty()) FontWeight.Bold else FontWeight.Normal
                )
            },
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .clickable(onClick = { dialogState = true })
        )
    }

    if (dialogState) {
        // TODO: Add initialize with selected value
        EDatePicker(onChange = {
            text = it
            onChange(InputState(text = it))
        }) {
            onDismiss.invoke()
            dialogState = false
        }
    }
}

@Composable
fun EDropDown(
    label: String,
    items: MutableList<String>,
    onChange: (InputState) -> Unit
) {
    var text by remember { mutableStateOf("") }
    var focused by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    val colors = TextFieldDefaults.textFieldColors(
        focusedLabelColor = Orange,
        unfocusedLabelColor = Color.Gray,
        errorLabelColor = "#ff6d64".color,
        focusedIndicatorColor = Orange,
        unfocusedIndicatorColor = OrangeVariant,
        errorIndicatorColor = "#ff6d64".color,
        errorTrailingIconColor = "#ff6d64".color
    )

    Box(
        Modifier
            .padding(8.dp)
            .clickable {
                expanded = true
            },
    ) {
        OutlinedTextField(
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    focused = it.isFocused
                    if (it.isFocused) {
                        expanded = true
                    }
                },
            colors = colors,
            value = text,
            onValueChange = { text = it },
            label = {
                Text(
                    text = if (focused || text.isNotEmpty()) label.uppercase() else label,
                    fontWeight = if (focused || text.isNotEmpty()) FontWeight.Bold else FontWeight.Normal
                )
            },
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .clickable(onClick = { expanded = true })
        )
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        items.forEach {
            DropdownMenuItem(onClick = {
                text = it
                onChange.invoke(InputState(it, true))
                expanded = false
            }) {
                Text(text = it)
            }
        }
    }
}

data class InputState(
    var text: String = "",
    var isSuccess: Boolean = false
)

@Preview(showBackground = true)
@Composable
fun TextFieldsPreview() {
    EdumyClientTheme {
        Column {
            ETextField(
                label = "Edumy Text Field",
                onChange = { },
                success = { it.isEmpty() },
                errorText = "Cannot be empty"
            )
            ESearchView(
                label = "Search View",
                onChange = {}
            )
            EDateField(
                label = "Date Field",
                onChange = {}
            )
            EDropDown(
                label = "Select",
                items = mutableListOf(),
                onChange = {}
            )
        }
    }
}