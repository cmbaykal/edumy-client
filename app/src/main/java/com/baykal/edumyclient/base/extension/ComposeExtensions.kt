package com.baykal.edumyclient.base.extension

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.isScrolledToEnd() = try {
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
} catch (e: Exception) {
    false
}
