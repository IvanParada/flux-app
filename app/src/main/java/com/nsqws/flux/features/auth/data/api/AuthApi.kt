package com.nsqws.flux.features.auth.data.api

import com.nsqws.flux.features.auth.data.models.LoginRequest
import com.nsqws.flux.features.auth.data.models.RegisterRequest
import com.nsqws.flux.features.auth.data.models.VerifyRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<Unit>

    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<Unit>

    @POST("auth/verify")
    suspend fun verify(
        @Body request: VerifyRequest
    ): Response<Unit>
}