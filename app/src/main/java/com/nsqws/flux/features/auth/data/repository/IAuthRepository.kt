package com.nsqws.flux.features.auth.data.repository

interface IAuthRepository {
    suspend fun register(email: String, password: String, rut: String): Result<Unit>
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun verify(email: String, code: String): Result<Unit>
    suspend fun forgotPassword(email: String): Result<Unit>
    suspend fun verifyResetCode(email: String, code: String): Result<Unit>
    suspend fun resetPassword(email: String, code: String, newPassword: String): Result<Unit>
    suspend fun resendCode(email: String): Result<Unit>

}