package com.baykal.edumyclient.base.extension

import androidx.annotation.DimenRes
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp

@Composable
fun fontDimensionResource(@DimenRes id: Int) = dimensionResource(id = id).value.sp

fun ScrollableState.isScrolledToEnd() = try {
    when (this) {
        is LazyListState -> {
            layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
        }
        is LazyGridState -> {
            layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
        }
        else -> false
    }
} catch (e: Exception) {
    false
}

suspend fun ScrollableState.scrollToEnd() = try {
    when (this) {
        is LazyListState -> {
            scrollToItem(layoutInfo.totalItemsCount - 1)
        }
        is LazyGridState -> {
            scrollToItem(layoutInfo.totalItemsCount - 1)
        }
        else -> Unit
    }
} catch (e: Exception) {
}
