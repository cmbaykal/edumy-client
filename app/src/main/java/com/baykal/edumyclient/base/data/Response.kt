package com.baykal.edumyclient.base.data

import com.google.gson.annotations.SerializedName


open class ApiResponse<out T>(
    @SerializedName("dialog")
    val dialog: EdumyDialog? = null,

    @SerializedName("result")
    val result: EdumyResult? = null,

    @SerializedName("data")
    val data: T
)

data class EdumyResult(
    @SerializedName("error") val error: String? = null,
    @SerializedName("success") val success: Boolean?
)

data class EdumyDialog(
    @SerializedName("title") val title: String,
    @SerializedName("message") val message: String,
    @SerializedName("buttons") val buttons: List<DialogButton>
)

data class DialogButton(
    @SerializedName("text") val text: String,
    @SerializedName("action") val action: ButtonAction? = ButtonAction.dismiss
)

enum class ButtonAction {
    @SerializedName("dismiss")
    dismiss,

    @SerializedName("retry")
    retry,

    @SerializedName("close")
    close;
}

