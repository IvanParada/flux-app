package com.nsqws.flux.features.auth.data.datasource

import com.nsqws.flux.features.auth.data.api.AuthApi
import com.nsqws.flux.features.auth.data.datasource.dto.LoginResponse
import com.nsqws.flux.features.auth.data.models.ForgotPasswordRequest
import com.nsqws.flux.features.auth.data.models.LoginRequest
import com.nsqws.flux.features.auth.data.models.RegisterRequest
import com.nsqws.flux.features.auth.data.models.ResendCodeRequest
import com.nsqws.flux.features.auth.data.models.ResetPasswordRequest
import com.nsqws.flux.features.auth.data.models.VerifyRequest
import com.nsqws.flux.features.auth.data.models.VerifyResetCodeRequest
import retrofit2.Response
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val authApi: AuthApi
) {
    suspend fun login(request: LoginRequest): Response<LoginResponse> {
        return authApi.login(request)
    }

    suspend fun register(request: RegisterRequest): Response<Unit> {
        return authApi.register(request)
    }

    suspend fun verify(request: VerifyRequest): Response<Unit> {
        return authApi.verify(request)
    }

    suspend fun forgotPassword(request: ForgotPasswordRequest): Response<Unit> {
        return authApi.forgotPassword(request)
    }
    suspend fun verifyResetCode(request: VerifyResetCodeRequest): Response<Unit> {
        return authApi.verifyResetCode(request)
    }

    suspend fun resetPassword(request: ResetPasswordRequest): Response<Unit> {
        return authApi.resetPassword(request)
    }

    suspend fun resendCode(request: ResendCodeRequest): Response<Unit> {
        return authApi.resendCode(request)
    }
}