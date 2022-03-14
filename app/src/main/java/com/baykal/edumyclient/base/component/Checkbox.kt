package com.baykal.edumyclient.base.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.baykal.edumyclient.base.ui.theme.Gray
import com.baykal.edumyclient.base.ui.theme.Orange

@Composable
fun ECheckbox(
    modifier: Modifier = Modifier,
    label: String? = null,
    onChecked: (Boolean) -> Unit
) {
    var checkState by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            colors = CheckboxDefaults.colors(
                checkedColor = Orange,
                uncheckedColor = Gray,
                checkmarkColor = Color.White
            ),
            checked = checkState,
            onCheckedChange = {
                checkState = !checkState
                onChecked.invoke(checkState)
            })
        label?.let {
            Text(
                text = label,
                color = Gray
            )
        }
    }
}