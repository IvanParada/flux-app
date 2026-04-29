package com.nsqws.flux.features.auth.data.remote.datasource

import com.nsqws.flux.features.auth.data.remote.api.AuthApi
import com.nsqws.flux.features.auth.data.remote.dto.response.LoginResponseDto
import com.nsqws.flux.features.auth.data.remote.dto.request.ForgotPasswordRequest
import com.nsqws.flux.features.auth.data.remote.dto.request.LoginRequest
import com.nsqws.flux.features.auth.data.remote.dto.request.RegisterRequest
import com.nsqws.flux.features.auth.data.remote.dto.request.ResendCodeRequest
import com.nsqws.flux.features.auth.data.remote.dto.request.ResetPasswordRequest
import com.nsqws.flux.features.auth.data.remote.dto.request.VerifyRequest
import com.nsqws.flux.features.auth.data.remote.dto.request.VerifyResetCodeRequest
import com.nsqws.flux.features.auth.data.remote.dto.response.ForgotPasswordResponseDto
import com.nsqws.flux.features.auth.data.remote.dto.response.RegisterResponseDto
import com.nsqws.flux.features.auth.data.remote.dto.response.ResendCodeResponseDto
import com.nsqws.flux.features.auth.data.remote.dto.response.ResetPasswordResponseDto
import com.nsqws.flux.features.auth.data.remote.dto.response.VerifyResetCodeResponseDto
import com.nsqws.flux.features.auth.data.remote.dto.response.VerifyResponseDto
import retrofit2.Response
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val authApi: AuthApi
) {
    suspend fun login(request: LoginRequest): Response<LoginResponseDto> {
        return authApi.login(request)
    }

    suspend fun register(request: RegisterRequest): Response<RegisterResponseDto> {
        return authApi.register(request)
    }

    suspend fun verify(request: VerifyRequest): Response<VerifyResponseDto> {
        return authApi.verify(request)
    }

    suspend fun forgotPassword(request: ForgotPasswordRequest): Response<ForgotPasswordResponseDto> {
        return authApi.forgotPassword(request)
    }
    suspend fun verifyResetCode(request: VerifyResetCodeRequest): Response<VerifyResetCodeResponseDto> {
        return authApi.verifyResetCode(request)
    }

    suspend fun resetPassword(request: ResetPasswordRequest): Response<ResetPasswordResponseDto> {
        return authApi.resetPassword(request)
    }

    suspend fun resendCode(request: ResendCodeRequest): Response<ResendCodeResponseDto> {
        return authApi.resendCode(request)
    }
}