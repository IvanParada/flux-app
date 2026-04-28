package com.nsqws.flux.features.auth.data.repository

import com.nsqws.flux.core.data.local.TokenManager
import com.nsqws.flux.features.auth.data.datasource.AuthRemoteDataSource
import com.nsqws.flux.features.auth.data.datasource.dto.LoginResponse
import com.nsqws.flux.features.auth.data.models.ForgotPasswordRequest
import com.nsqws.flux.features.auth.data.models.LoginRequest
import com.nsqws.flux.features.auth.data.models.RegisterRequest
import com.nsqws.flux.features.auth.data.models.ResendCodeRequest
import com.nsqws.flux.features.auth.data.models.ResetPasswordRequest
import com.nsqws.flux.features.auth.data.models.VerifyRequest
import com.nsqws.flux.features.auth.data.models.VerifyResetCodeRequest
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource,
    private val tokenManager: TokenManager
) : IAuthRepository {

    private fun getErrorMessage(response: retrofit2.Response<*>): String {
        return try {
            val errorJson = response.errorBody()?.string()

            if (!errorJson.isNullOrBlank()) {
                val jsonObject = org.json.JSONObject(errorJson)
                jsonObject.optString("message", "Error: ${response.code()}")
            } else {
                "Error: ${response.code()}"
            }
        } catch (e: Exception) {
            "Error: ${response.code()}"
        }
    }

    override suspend fun register(email: String, password: String, rut: String): Result<Unit> {
        return try {
            val response = remoteDataSource.register(RegisterRequest(email, password, rut))
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                val errorMsg = getErrorMessage(response)
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            val response = remoteDataSource.login(LoginRequest(email, password))

            if (response.isSuccessful) {
                val authResponse = response.body()
                authResponse?.accessToken?.let { token ->
                    tokenManager.saveToken(token)
                }
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun verify(email: String, code: String): Result<Unit> {
        return try {
            val response = remoteDataSource.verify(VerifyRequest(email, code))

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun forgotPassword(email: String): Result<Unit> {
        return try {
            val response = remoteDataSource.forgotPassword(ForgotPasswordRequest(email))

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun verifyResetCode(email: String, code: String): Result<Unit> {
        return try {
            val response = remoteDataSource.verifyResetCode(VerifyResetCodeRequest(email, code))

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    override suspend fun resetPassword(email: String, code: String, newPassword: String): Result<Unit> {
        return try {
            val response = remoteDataSource.resetPassword(
                ResetPasswordRequest(
                    email,
                    code,
                    newPassword
                )
            )

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun resendCode(email: String): Result<Unit> {
        return try {
            val response = remoteDataSource.resendCode(
                ResendCodeRequest(email)
            )

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }





}
