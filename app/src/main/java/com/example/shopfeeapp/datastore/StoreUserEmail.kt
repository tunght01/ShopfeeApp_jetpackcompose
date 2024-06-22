package com.example.shopfeeapp.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.shopfeeapp.model.User
import com.example.shopfeeapp.model.UserRespone
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map


class StoreUserEmail(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserEmail")
        val USER_EMAIL_KEY = stringPreferencesKey("user_email")
    }

    // nhận email
    suspend fun fetchEmail(): String {
        return context.dataStore.data
            .map { preferences ->
                preferences[USER_EMAIL_KEY] ?: ""
            }.firstOrNull() ?: ""
    }

    // lưu email
    suspend fun saveEmail(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL_KEY] = name
        }
    }
}

class StoreUser(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("User")
        val USER_OBJECT_KEY = stringPreferencesKey("user_object")
    }

    private val gson = Gson()

    // Nhận object User
    suspend fun fetchUser(): User? {
        val userJson = context.dataStore.data
            .map { preferences ->
                preferences[USER_OBJECT_KEY] ?: ""
            }.firstOrNull() ?: ""

        return if (userJson.isNotEmpty()) gson.fromJson(userJson, User::class.java) else null
    }

    // Lưu object User
    suspend fun saveUser(user: User) {
        val userJson = gson.toJson(user)
        context.dataStore.edit { preferences ->
            preferences[USER_OBJECT_KEY] = userJson
        }
    }
}