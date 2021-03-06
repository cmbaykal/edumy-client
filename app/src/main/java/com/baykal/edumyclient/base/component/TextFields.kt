package com.baykal.edumyclient.base.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.extension.fontDimensionResource
import com.baykal.edumyclient.base.ui.theme.*

data class InputState(
    var text: String = "",
    var isSuccess: Boolean = false
)

@Composable
fun ETextFieldColors() = TextFieldDefaults.textFieldColors(
    focusedLabelColor = Orange,
    unfocusedLabelColor = Color.Gray,
    errorLabelColor = ErrorColor,
    focusedIndicatorColor = Orange,
    unfocusedIndicatorColor = OrangeVariant,
    errorIndicatorColor = ErrorColor,
    errorTrailingIconColor = ErrorColor
)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ETextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String = "",
    errorText: String? = null,
    maxLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction? = null,
    onChange: (InputState) -> Unit,
    success: ((text: String) -> Boolean) = { true },
    onAction: (() -> Unit)? = null,
    passwordToggle: Boolean = false
) {
    var text by remember { mutableStateOf(value) }
    var focused by remember { mutableStateOf(false) }
    var isSuccess by remember { mutableStateOf(success.invoke(value)) }
    var charVisibility by remember { mutableStateOf(!passwordToggle) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val labelSize = if (focused) {
        fontDimensionResource(id = R.dimen.font_size_tiny)
    } else {
        fontDimensionResource(id = R.dimen.font_size_medium)
    }

    onChange.invoke(InputState(text, isSuccess))

    Column(
        Modifier.padding(dimensionResource(id = R.dimen.padding_standard)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.text_field_default_height) * maxLines)
                .onFocusChanged { focused = it.isFocused }
                .then(modifier),
            textStyle = TextStyle(
                fontSize = fontDimensionResource(id = R.dimen.text_field_text_size),
                textDecoration = TextDecoration.None
            ),
            colors = ETextFieldColors(),
            maxLines = maxLines,
            label = {
                Text(
                    text = label,
                    fontWeight = if (focused || text.isNotEmpty()) FontWeight.Bold else FontWeight.Normal,
                    fontSize = labelSize,
                )
            },
            value = text,
            onValueChange = { value ->
                when (keyboardType) {
                    KeyboardType.Text -> {
                        text = value
                    }
                    KeyboardType.Number -> {
                        text = value.filter { it.isDigit() }
                    }
                }
                isSuccess = success.invoke(text)
                onChange.invoke(InputState(text, isSuccess))
            },
            visualTransformation = if (charVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Row(
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_standard)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (text.isNotEmpty() && !focused) {
                        Icon(
                            imageVector = if (isSuccess) Icons.Default.Check else Icons.Default.Close,
                            tint = if (isSuccess) SuccessColor else ErrorColor,
                            contentDescription = ""
                        )
                    }
                    if (passwordToggle && focused) {
                        IconButton(
                            modifier = Modifier.size(dimensionResource(id = R.dimen.text_field_icon_size)),
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
            keyboardOptions = KeyboardOptions(
                imeAction = imeAction ?: ImeAction.None,
                keyboardType = keyboardType
            ),
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
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_medium),
                        start = dimensionResource(id = R.dimen.padding_big)
                    ),
                fontSize = fontDimensionResource(id = R.dimen.font_size_small),
                textAlign = TextAlign.Start,
                text = errorText,
                color = ErrorColor
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ESearchView(
    modifier: Modifier = Modifier,
    placeHolder: String,
    textSize: TextUnit = fontDimensionResource(id = R.dimen.font_size_medium),
    textColor: Color = Color.Black,
    borderColor: Color = Orange,
    value: String = "",
    onChange: (String) -> Unit,
    onAction: (() -> Unit)? = null,
) {
    val marginStandard = dimensionResource(id = R.dimen.margin_standard)
    val marginMedium = dimensionResource(id = R.dimen.margin_medium)

    val constraintSet: ConstraintSet = ConstraintSet {
        val inputRef = createRefFor("input")
        val placeHolderRef = createRefFor("placeHolder")
        val iconRef = createRefFor("icon")
        constrain(inputRef) {
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(anchor = parent.start, margin = marginStandard)
            end.linkTo(anchor = iconRef.start, margin = marginStandard)
        }
        constrain(placeHolderRef) {
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(anchor = parent.start, margin = marginStandard)
            end.linkTo(anchor = iconRef.start, margin = marginStandard)
        }
        constrain(iconRef) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            end.linkTo(anchor = parent.end, margin = marginMedium)
        }
    }

    var text by remember { mutableStateOf(value) }
    var focused by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val borderWidth = if (focused)
        dimensionResource(id = R.dimen.border_stroke_big)
    else
        dimensionResource(id = R.dimen.border_stroke_small)

    ConstraintLayout(
        modifier = modifier.then(
            Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.padding_standard),
                    end = dimensionResource(id = R.dimen.padding_standard)
                )
                .border(
                    border = BorderStroke(
                        width = borderWidth,
                        color = borderColor
                    ),
                    shape = RoundedCornerShape(20)
                )
                .clip(RoundedCornerShape(20))
                .height(dimensionResource(id = R.dimen.search_view_height))
                .fillMaxWidth()
                .background(color = GrayLight)
        ),
        constraintSet = constraintSet,
    ) {
        BasicTextField(
            modifier = Modifier
                .onFocusChanged {
                    focused = it.isFocused
                }
                .layoutId("input"),
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Start,
                color = textColor,
                fontSize = textSize
            ),
            singleLine = true,
            value = text,
            onValueChange = {
                text = it
                onChange.invoke(it)
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
        if (text.isEmpty()) {
            Text(
                modifier = Modifier
                    .alpha(0.5f)
                    .layoutId("placeHolder"),
                text = placeHolder,
                fontSize = textSize,
                color = textColor,
                textAlign = TextAlign.Start
            )
        }
        EIconButton(
            modifier = Modifier.layoutId("icon"),
            icon = if (text.isEmpty()) Icons.Filled.Search else Icons.Filled.Close,
            iconColor = if (focused) Orange else Gray,
            enabled = text.isNotEmpty(),
        ) {
            text = ""
            onChange.invoke(text)
        }
    }
}

@Composable
fun EDialogField(
    label: String,
    value: String? = "",
    buttonLabel: String = "Okay",
    dialogState: MutableState<Boolean> = mutableStateOf(false),
    onClick: () -> Unit = {},
    onButtonClick: () -> Unit = {},
    onDismiss: () -> Unit = {},
    dialogContent: @Composable () -> Unit = {},
) {
    Box(
        Modifier
            .padding(dimensionResource(id = R.dimen.padding_standard))
            .clickable(onClick = onClick)
    ) {
        OutlinedTextField(
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.text_field_default_height))
                .onFocusChanged {
                    if (it.isFocused) {
                        onClick.invoke()
                    }
                },
            textStyle = TextStyle(
                fontSize = fontDimensionResource(id = R.dimen.text_field_text_size),
                textDecoration = TextDecoration.None
            ),
            colors = ETextFieldColors(),
            value = value.toString(),
            onValueChange = {},
            label = {
                Text(
                    text = label,
                    fontWeight = FontWeight.Normal,
                    fontSize = fontDimensionResource(id = R.dimen.font_size_medium)
                )
            },
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .clickable(onClick = onClick)
        )
    }

    if (dialogState.value) {
        Dialog(onDismissRequest = onDismiss) {
            Surface(
                modifier = Modifier
                    .wrapContentHeight()
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = LinearEasing
                        )
                    ),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.radius_standard))
            ) {
                Column {
                    dialogContent()
                    ETextButton(
                        modifier = Modifier.align(Alignment.End),
                        text = buttonLabel,
                        onClick = {
                            onButtonClick.invoke()
                            onDismiss.invoke()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun EDropDown(
    label: String,
    items: MutableList<String>,
    itemPrefix: String = "",
    itemSuffix: String = "",
    selected: String? = null,
    onChange: (InputState) -> Unit
) {
    var text by remember { mutableStateOf(selected ?: "") }
    var expanded by remember { mutableStateOf(false) }

    Box(
        Modifier
            .padding(dimensionResource(id = R.dimen.padding_standard))
            .clickable {
                expanded = true
            },
    ) {
        OutlinedTextField(
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.text_field_default_height))
                .onFocusChanged {
                    if (it.isFocused) {
                        expanded = true
                    }
                },
            textStyle = TextStyle(
                fontSize = fontDimensionResource(id = R.dimen.text_field_text_size),
                textDecoration = TextDecoration.None
            ),
            colors = ETextFieldColors(),
            value = text,
            onValueChange = { text = it },
            label = {
                Text(
                    text = label,
                    fontWeight = FontWeight.Normal,
                    fontSize = fontDimensionResource(id = R.dimen.font_size_medium)
                )
            },
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .clickable(onClick = { expanded = true })
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach {
                val string = "$itemPrefix $it $itemSuffix".trim()

                DropdownMenuItem(
                    contentPadding = PaddingValues(
                        horizontal = 0.dp,
                        vertical = 0.dp
                    ),
                    onClick = {
                        text = string
                        onChange.invoke(InputState(it, true))
                        expanded = false
                    }) {
                    Text(
                        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_standard)),
                        text = string,
                        fontSize = fontDimensionResource(id = R.dimen.font_size_medium)
                    )
                }
            }
        }
    }
}

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
                placeHolder = "Search View",
                onChange = {}
            )
            EDialogField(
                label = "Date Field"
            )
            EDropDown(
                label = "Select",
                items = mutableListOf(),
                onChange = {}
            )
        }
    }
}