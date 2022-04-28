package com.baykal.edumyclient.base.extension

import androidx.annotation.DimenRes
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp

@Composable
fun fontDimensionResource(@DimenRes id: Int) = dimensionResource(id = id).value.sp

fun LazyListState.isScrolledToEnd() = try {
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
} catch (e: Exception) {
    false
}
