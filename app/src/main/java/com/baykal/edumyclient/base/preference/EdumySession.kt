package com.baykal.edumyclient.base.preference

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.baykal.edumyclient.data.model.user.response.AuthTokenResponse
import com.baykal.edumyclient.data.model.user.response.User
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class EdumySession(context: Context) {
    private val preferences = EncryptedSharedPreferences.create(
        context,
        FILE_NAME,
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveUser(user: User) {
        val userData = Json.encodeToString(user)
        preferences.edit {
            putString(USER, userData)
            commit()
        }
    }

    private fun deleteUser() {
        preferences.edit {
            remove(USER)
        }
    }

    fun saveToken(authToken: AuthTokenResponse) {
        preferences.edit {
            putString(TOKEN, authToken.token)
            commit()
        }
    }

    private fun deleteToken() {
        preferences.edit {
            remove(TOKEN)
        }
    }

    fun drop() {
        deleteToken()
        deleteUser()
    }

    val user: User? get() = preferences.getString(USER, null)?.let {
            Json.decodeFromString<User>(it)
        } ?: run {
            null
        }


    val token get() = preferences.getString(TOKEN, null)

    companion object {
        const val FILE_NAME = "edumy_auth"
        const val USER = "edumy_user"
        const val TOKEN = "edumy_token"
    }
}

fun <T> EdumySession.withUserId(block: (String) -> T): T? {
    return user?.id?.let {
        block(it)
    } ?: run {
        null
    }
}

fun <T> EdumySession.withUser(block: (User) -> T): T? {
    return user?.let {
        block(it)
    } ?: run {
        null
    }
}

