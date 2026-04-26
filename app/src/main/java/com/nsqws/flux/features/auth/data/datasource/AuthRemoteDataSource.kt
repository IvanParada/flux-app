package com.nsqws.flux.features.auth.data.datasource

import com.nsqws.flux.features.auth.data.api.AuthApi
import com.nsqws.flux.features.auth.data.models.LoginRequest
import com.nsqws.flux.features.auth.data.models.RegisterRequest
import com.nsqws.flux.features.auth.data.models.VerifyRequest
import retrofit2.Response
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val authApi: AuthApi
) {
    suspend fun login(request: LoginRequest): Response<Unit> {
        return authApi.login(request)
    }

    suspend fun register(request: RegisterRequest): Response<Unit> {
        return authApi.register(request)
    }

    suspend fun verify(request: VerifyRequest): Response<Unit> {
        return authApi.verify(request)
    }

}