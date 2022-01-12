package com.baykal.edumyclient.data.model.account

import com.baykal.edumyclient.base.data.BaseResponse
import com.baykal.edumyclient.data.model.account.user.UserModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    @SerialName("data") val data: UserModel? = null
) : BaseResponse()