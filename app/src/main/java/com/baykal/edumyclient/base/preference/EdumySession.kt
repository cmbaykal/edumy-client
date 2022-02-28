package com.baykal.edumyclient.base.preference

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.baykal.edumyclient.data.model.user.response.AuthTokenResponse

class EdumySession(context: Context) {
    private val preferences = EncryptedSharedPreferences.create(
        context,
        FILE_NAME,
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveToken(authToken: AuthTokenResponse) {
        preferences.edit {
            putString(TOKEN_KEY, authToken.token)
            commit()
        }
    }

    val token get() = preferences.getString(TOKEN_KEY, null)

    companion object {
        const val FILE_NAME = "edumy_auth"
        const val TOKEN_KEY = "edumy_token"
    }
}