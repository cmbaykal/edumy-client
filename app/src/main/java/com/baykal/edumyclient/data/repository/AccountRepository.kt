package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.base.data.ApiResponse
import com.baykal.edumyclient.base.data.BaseRepository
import com.baykal.edumyclient.data.model.user.request.LoginCredentials
import com.baykal.edumyclient.data.model.user.request.PasswordCredentials
import com.baykal.edumyclient.data.model.user.request.RegisterCredentials
import com.baykal.edumyclient.data.model.user.request.UpdateCredentials
import com.baykal.edumyclient.data.model.user.response.User
import kotlinx.coroutines.flow.Flow

abstract class AccountRepository : BaseRepository() {
    abstract fun registerUser(registerCredentials: RegisterCredentials): Flow<ApiResponse<out Unit>>
    abstract fun loginUser(loginCredentials: LoginCredentials): Flow<ApiResponse<out User>>
    abstract fun updateUser(updateCredentials: UpdateCredentials): Flow<ApiResponse<out Unit>>
    abstract fun changePassword(passwordCredentials: PasswordCredentials): Flow<ApiResponse<out Unit>>
    abstract fun getUserInformation(userId: String): Flow<ApiResponse<out User>>
}