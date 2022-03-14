package com.baykal.edumyclient.base.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.baykal.edumyclient.base.ui.theme.Orange

@Composable
fun EdumyTabRow(
    modifier: Modifier = Modifier,
    data: MutableList<String>,
    selected: Int = 0,
    onSelect: (String) -> Unit
) {
    var tabIndex by remember { mutableStateOf(selected) }

    ScrollableTabRow(
        modifier = modifier,
        backgroundColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                color = Orange,
            )
        },
        selectedTabIndex = tabIndex,
    ) {
        data.forEachIndexed { index, data ->
            Box(
                modifier = Modifier
                    .padding(
                        start = 5.dp,
                        end = 5.dp,
                        top = 10.dp,
                        bottom = 10.dp
                    )
                    .border(
                        border = BorderStroke(2.dp, Orange),
                        shape = RoundedCornerShape(50)
                    )
                    .clip(RoundedCornerShape(50))
                    .clickable {
                        tabIndex = index
                        onSelect.invoke(data)
                    }
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Center),
                    text = data,
                    color = Orange
                )
            }
        }
    }
}
