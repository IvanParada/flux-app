package com.nsqws.flux.features.auth.data.api

import com.nsqws.flux.features.auth.data.datasource.dto.LoginResponse
import com.nsqws.flux.features.auth.data.models.ForgotPasswordRequest
import com.nsqws.flux.features.auth.data.models.LoginRequest
import com.nsqws.flux.features.auth.data.models.RegisterRequest
import com.nsqws.flux.features.auth.data.models.ResendCodeRequest
import com.nsqws.flux.features.auth.data.models.ResetPasswordRequest
import com.nsqws.flux.features.auth.data.models.VerifyRequest
import com.nsqws.flux.features.auth.data.models.VerifyResetCodeRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<Unit>

    @POST("auth/verify")
    suspend fun verify(
        @Body request: VerifyRequest
    ): Response<Unit>

    @POST("auth/forgot-password")
    suspend fun forgotPassword(
        @Body request: ForgotPasswordRequest
    ): Response<Unit>

    @POST("auth/verify-reset-code")
    suspend fun verifyResetCode(
        @Body request: VerifyResetCodeRequest
    ): Response<Unit>

    @POST("auth/reset-password")
    suspend fun resetPassword(
        @Body request: ResetPasswordRequest
    ): Response<Unit>

    @POST("auth/resend-code")
    suspend fun resendCode(
        @Body request: ResendCodeRequest
    ): Response<Unit>
}