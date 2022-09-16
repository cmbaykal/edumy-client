package com.baykal.edumyclient.base.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.dimensionResource
import com.baykal.edumyclient.R
import com.baykal.edumyclient.base.extension.fontDimensionResource
import com.baykal.edumyclient.base.extension.isScrolledToEnd
import com.baykal.edumyclient.base.extension.scrollToEnd
import com.baykal.edumyclient.base.ui.theme.Orange
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

// region Tab Row

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
                start = dimensionResource(id = R.dimen.padding_medium),
                end = dimensionResource(id = R.dimen.padding_medium),
                top = dimensionResource(id = R.dimen.padding_standard),
                bottom = dimensionResource(id = R.dimen.padding_standard)
            )
            .border(
                border = BorderStroke(dimensionResource(id = R.dimen.border_stroke_standard), Orange),
                shape = RoundedCornerShape(50)
            )
            .clip(RoundedCornerShape(50))
            .clickable {
                onClick.invoke()
            }
    ) {
        Text(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_standard))
                .align(Alignment.Center),
            fontSize = fontDimensionResource(id = R.dimen.font_size_small),
            text = data,
            color = Orange
        )
    }
}

// endregion

// region List

enum class ListOrientation {
    Vertical,
    Horizontal
}

sealed class ListType {
    data class Default(val orientation: ListOrientation = ListOrientation.Vertical) : ListType()
    data class Grid(val spanCount: Int = 2) : ListType()
}

data class ListSwipeRefreshSettings(
    val enabled: Boolean,
    val onRefresh: () -> Unit,
) {
    companion object {
        val Default
            get() = ListSwipeRefreshSettings(
                enabled = true,
                onRefresh = {}
            )
    }
}

data class ListLoadMoreSettings(
    val enabled: Boolean,
    val onLoadMore: () -> Unit,
    val loadMoreContent: @Composable () -> Unit,
    val endContent: @Composable () -> Unit,
) {
    companion object {
        val Default
            get() = ListLoadMoreSettings(
                enabled = false,
                onLoadMore = {},
                loadMoreContent = {},
                endContent = {}
            )
    }
}

@Composable
fun <T> EList(
    modifier: Modifier = Modifier,
    scrollState: ScrollableState,
    listType: ListType = ListType.Default(),
    swipeRefreshSettings: ListSwipeRefreshSettings = ListSwipeRefreshSettings.Default,
    loadMoreSettings: ListLoadMoreSettings = ListLoadMoreSettings.Default,
    listItems: List<T>? = null,
    itemContent: @Composable (item: T) -> Unit
) {
    val swipeRefreshState: SwipeRefreshState = rememberSwipeRefreshState(false)

    val scrollEndState by remember {
        derivedStateOf {
            scrollState.isScrolledToEnd()
        }
    }

    LaunchedEffect(scrollEndState) {
        if (scrollEndState && loadMoreSettings.enabled) {
            loadMoreSettings.onLoadMore()
            scrollState.scrollToEnd()
        }
    }

    SwipeRefresh(
        state = swipeRefreshState,
        swipeEnabled = swipeRefreshSettings.enabled,
        onRefresh = swipeRefreshSettings.onRefresh
    ) {
        when (listType) {
            is ListType.Default -> {
                if (listType.orientation == ListOrientation.Vertical) {
                    LazyColumn(
                        modifier = modifier,
                        state = scrollState as LazyListState
                    ) {
                        listItems?.let {
                            items(listItems) { item ->
                                itemContent.invoke(item)
                            }
                            item {
                                if (scrollEndState && loadMoreSettings.enabled) {
                                    loadMoreSettings.loadMoreContent()
                                } else if (!loadMoreSettings.enabled) {
                                    loadMoreSettings.endContent()
                                }
                            }
                        }
                    }
                } else {
                    LazyRow(
                        modifier = modifier,
                        state = scrollState as LazyListState
                    ) {
                        listItems?.let {
                            items(listItems) { item ->
                                itemContent.invoke(item)
                            }
                            item {
                                if (scrollEndState && loadMoreSettings.enabled) {
                                    loadMoreSettings.loadMoreContent()
                                } else if (!loadMoreSettings.enabled) {
                                    loadMoreSettings.endContent()
                                }
                            }
                        }
                    }
                }
            }

            is ListType.Grid -> {
                Column {
                    LazyVerticalGrid(
                        modifier = modifier,
                        columns = GridCells.Fixed(listType.spanCount),
                        state = scrollState as LazyGridState
                    ) {
                        listItems?.let {
                            items(listItems) { item ->
                                itemContent.invoke(item)
                            }
                        }
                    }
                    if (scrollEndState && loadMoreSettings.enabled) {
                        loadMoreSettings.loadMoreContent()
                    } else if (!loadMoreSettings.enabled) {
                        loadMoreSettings.endContent()
                    }
                }
            }
        }
    }
}

// endregion
