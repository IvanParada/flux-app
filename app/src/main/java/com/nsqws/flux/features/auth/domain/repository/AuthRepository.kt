package com.nsqws.flux.features.auth.domain.repository

import com.nsqws.flux.features.auth.data.remote.dto.response.ForgotPasswordResponseDto
import com.nsqws.flux.features.auth.data.remote.dto.response.LoginResponseDto
import com.nsqws.flux.features.auth.data.remote.dto.response.RegisterResponseDto
import com.nsqws.flux.features.auth.data.remote.dto.response.ResendCodeResponseDto
import com.nsqws.flux.features.auth.data.remote.dto.response.ResetPasswordResponseDto
import com.nsqws.flux.features.auth.data.remote.dto.response.VerifyResetCodeResponseDto
import com.nsqws.flux.features.auth.data.remote.dto.response.VerifyResponseDto

interface AuthRepository {
    suspend fun register(email: String, password: String, rut: String): Result<RegisterResponseDto>
    suspend fun login(email: String, password: String): Result<LoginResponseDto>
    suspend fun verify(email: String, code: String): Result<VerifyResponseDto>
    suspend fun forgotPassword(email: String): Result<ForgotPasswordResponseDto>
    suspend fun verifyResetCode(email: String, code: String): Result<VerifyResetCodeResponseDto>
    suspend fun resetPassword(email: String, code: String, newPassword: String): Result<ResetPasswordResponseDto>
    suspend fun resendCode(email: String): Result<ResendCodeResponseDto>

}