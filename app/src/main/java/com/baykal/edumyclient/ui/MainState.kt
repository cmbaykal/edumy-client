package com.baykal.edumyclient.ui

data class MainState(
    val startRoute: String? = null,
    val pageTitle: String = "",
    val bottomBarVisibility: Boolean = false,
    val topBarVisibility: Boolean = false,
    val menuVisibility: Boolean = true,
    val loadingState: Boolean = false,
    val dialogState: DialogState? = null,
)

data class DialogState(
    val title: String,
    val message: String,
    val onDismiss: () -> Unit = {}
)