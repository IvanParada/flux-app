package com.nsqws.flux.core.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "flux_prefs")

@Singleton
class TokenManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val tokenKey = stringPreferencesKey("auth_token")
    private val userIdKey = stringPreferencesKey("user_id")
    private val userRutKey = stringPreferencesKey("user_rut")

    val token: Flow<String?> = context.dataStore.data.map { it[tokenKey] }
    val userId: Flow<String?> = context.dataStore.data.map { it[userIdKey] }
    val userRut: Flow<String?> = context.dataStore.data.map { it[userRutKey] }

    suspend fun saveSession(token: String, userId: String, userRut: String) {
        context.dataStore.edit { prefs ->
            prefs[tokenKey] = token
            prefs[userIdKey] = userId
            prefs[userRutKey] = userRut
        }
    }

    suspend fun clearAll() {
        context.dataStore.edit { it.clear() }
    }
}