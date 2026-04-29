package com.nsqws.flux.features.auth.data.remote.api

import com.nsqws.flux.features.auth.data.remote.dto.request.ForgotPasswordRequest
import com.nsqws.flux.features.auth.data.remote.dto.request.LoginRequest
import com.nsqws.flux.features.auth.data.remote.dto.request.RegisterRequest
import com.nsqws.flux.features.auth.data.remote.dto.request.ResendCodeRequest
import com.nsqws.flux.features.auth.data.remote.dto.request.ResetPasswordRequest
import com.nsqws.flux.features.auth.data.remote.dto.request.VerifyRequest
import com.nsqws.flux.features.auth.data.remote.dto.request.VerifyResetCodeRequest
import com.nsqws.flux.features.auth.data.remote.dto.response.ForgotPasswordResponseDto
import com.nsqws.flux.features.auth.data.remote.dto.response.LoginResponseDto
import com.nsqws.flux.features.auth.data.remote.dto.response.RegisterResponseDto
import com.nsqws.flux.features.auth.data.remote.dto.response.ResendCodeResponseDto
import com.nsqws.flux.features.auth.data.remote.dto.response.ResetPasswordResponseDto
import com.nsqws.flux.features.auth.data.remote.dto.response.VerifyResetCodeResponseDto
import com.nsqws.flux.features.auth.data.remote.dto.response.VerifyResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponseDto>

    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
        ): Response<RegisterResponseDto>

    @POST("auth/verify")
    suspend fun verify(
        @Body request: VerifyRequest
    ): Response<VerifyResponseDto>

    @POST("auth/forgot-password")
    suspend fun forgotPassword(
        @Body request: ForgotPasswordRequest
    ): Response<ForgotPasswordResponseDto>

    @POST("auth/verify-reset-code")
    suspend fun verifyResetCode(
        @Body request: VerifyResetCodeRequest
    ): Response<VerifyResetCodeResponseDto>

    @POST("auth/reset-password")
    suspend fun resetPassword(
        @Body request: ResetPasswordRequest
    ): Response<ResetPasswordResponseDto>

    @POST("auth/resend-code")
    suspend fun resendCode(
        @Body request: ResendCodeRequest
    ): Response<ResendCodeResponseDto>
}