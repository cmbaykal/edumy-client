package com.baykal.edumyclient.base.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
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
import androidx.compose.ui.unit.sp
import com.baykal.edumyclient.base.extension.isScrolledToEnd
import com.baykal.edumyclient.base.ui.theme.Orange
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ETabRow(
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
            ETabRowItem(data = data) {
                tabIndex = index
                onSelect.invoke(data)
            }
        }
    }
}

@Composable
fun ETabRowItem(
    data: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(
                start = 5.dp,
                end = 5.dp,
                top = 8.dp,
                bottom = 8.dp
            )
            .border(
                border = BorderStroke(2.dp, Orange),
                shape = RoundedCornerShape(50)
            )
            .clip(RoundedCornerShape(50))
            .clickable {
                onClick.invoke()
            }
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center),
            fontSize = 12.sp,
            text = data,
            color = Orange
        )
    }
}

@Composable
fun <T> EList(
    modifier: Modifier = Modifier,
    isVertical: Boolean = true,
    scrollState: LazyListState = rememberLazyListState(),
    swipeRefreshState: SwipeRefreshState = rememberSwipeRefreshState(false),
    swipeRefresh: Boolean = false,
    onRefresh: () -> Unit = {},
    loadMore: Boolean = false,
    onLoadMore: () -> Unit = {},
    loadMoreContent: @Composable () -> Unit = {},
    endContent: @Composable () -> Unit = {},
    items: MutableList<T> = mutableListOf(),
    itemContent: @Composable (item: T) -> Unit
) {
    val scrollEndState by remember {
        derivedStateOf {
            with(scrollState) {
                isScrolledToEnd()
            }
        }
    }

    LaunchedEffect(scrollEndState) {
        if (scrollEndState && loadMore) {
            onLoadMore.invoke()
            scrollState.scrollToItem(scrollState.layoutInfo.totalItemsCount - 1)
        }
    }

    SwipeRefresh(
        state = swipeRefreshState,
        swipeEnabled = swipeRefresh,
        onRefresh = onRefresh
    ) {
        if (isVertical) {
            LazyColumn(
                modifier = modifier,
                state = scrollState
            ) {
                items(items) { item ->
                    itemContent.invoke(item)
                }
                item {
                    if (scrollEndState && loadMore) {
                        loadMoreContent()
                    } else if (!loadMore) {
                        endContent()
                    }
                }
            }
        } else {
            LazyRow(
                modifier = modifier,
                state = scrollState
            ) {
                items(items) { item ->
                    itemContent.invoke(item)
                }
                item {
                    if (scrollEndState && loadMore) {
                        loadMoreContent()
                    } else if (!loadMore) {
                        endContent()
                    }
                }
            }
        }
    }
}
