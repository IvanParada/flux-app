package com.nsqws.flux.features.auth.data.remote

import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val authApi: AuthApi
) {
    suspend fun register(rut: String) = authApi.register(mapOf("rut" to rut))
    suspend fun login(rut: String) = authApi.login(mapOf("rut" to rut))
}