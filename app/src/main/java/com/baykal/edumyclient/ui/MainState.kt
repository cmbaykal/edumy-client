package com.baykal.edumyclient.ui

data class MainState(
    val title: String = "",
    val bottomBarVisibility: Boolean = false,
    val topBarVisibility: Boolean = false,
    val loading: Boolean = false,
    val dialog: DialogState? = null
)

data class DialogState(
    val title: String,
    val message: String,
    val onDismiss: () -> Unit = {}
)