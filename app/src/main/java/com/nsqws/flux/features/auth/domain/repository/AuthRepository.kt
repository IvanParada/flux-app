package com.nsqws.flux.features.auth.domain.repository

import com.nsqws.flux.features.auth.domain.model.AuthMessage
import com.nsqws.flux.features.auth.domain.model.AuthSession

interface AuthRepository {
    suspend fun register(email: String, password: String, rut: String): Result<AuthMessage>
    suspend fun login(email: String, password: String): Result<AuthSession>
    suspend fun verify(email: String, code: String): Result<AuthMessage>
    suspend fun forgotPassword(email: String): Result<AuthMessage>
    suspend fun verifyResetCode(email: String, code: String): Result<AuthMessage>
    suspend fun resetPassword(email: String, code: String, newPassword: String): Result<AuthMessage>
    suspend fun resendCode(email: String): Result<AuthMessage>

}