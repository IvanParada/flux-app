package com.nsqws.flux.features.auth.data.repository

import androidx.compose.ui.viewinterop.NoOpUpdate
import com.nsqws.flux.core.data.local.TokenManager
import com.nsqws.flux.features.auth.data.remote.datasource.AuthRemoteDataSource
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
import com.nsqws.flux.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource,
    private val tokenManager: TokenManager
) : AuthRepository {

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

    override suspend fun register(email: String, password: String, rut: String): Result<RegisterResponseDto> {
        return try {
            val response = remoteDataSource.register(RegisterRequest(email, password, rut))
            if (response.isSuccessful) {
                val authResponse = response.body()

                if (authResponse != null) {
                    Result.success(authResponse)
                } else {
                    Result.failure(Exception("Empty body"))
                }
            } else {
                val errorMsg = getErrorMessage(response)
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun login(email: String, password: String ): Result<LoginResponseDto> {
        return try {
            val response = remoteDataSource.login(
                LoginRequest(email, password)
            )

            if (response.isSuccessful) {
                val authResponse = response.body()

                if (authResponse != null) {
                    authResponse.accessToken.let { token ->
                        tokenManager.saveToken(token)
                    }

                    Result.success(authResponse)
                } else {
                    Result.failure(Exception("Respuesta vacía"))
                }
            } else {
                val errorMsg = getErrorMessage(response)
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun verify(email: String, code: String): Result<VerifyResponseDto> {
        return try {
            val response = remoteDataSource.verify(VerifyRequest(email, code))

            if (response.isSuccessful) {
                val authResponse = response.body()

                if(authResponse != null)
                    Result.success(authResponse)
                else
                    Result.failure(Exception("Empty body"))
            } else {
                val errorMsg = getErrorMessage(response)
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun forgotPassword(email: String): Result<ForgotPasswordResponseDto> {
        return try {
            val response = remoteDataSource.forgotPassword(ForgotPasswordRequest(email))

            if (response.isSuccessful) {
                val authResponse = response.body()

                if(authResponse != null)
                    Result.success(authResponse)
                else
                    Result.failure(Exception("Empty body"))
            } else {
                val errorMsg = getErrorMessage(response)
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun verifyResetCode(email: String, code: String): Result<VerifyResetCodeResponseDto> {
        return try {
            val response = remoteDataSource.verifyResetCode(VerifyResetCodeRequest(email, code))

            if (response.isSuccessful) {
                val authResponse = response.body()

                if (authResponse != null)
                    Result.success(authResponse)
                else
                    Result.failure(Exception("Empty body"))
            } else {
                val errorMsg = getErrorMessage(response)
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    override suspend fun resetPassword(email: String, code: String, newPassword: String): Result<ResetPasswordResponseDto> {
        return try {
            val response = remoteDataSource.resetPassword(
                ResetPasswordRequest(
                    email,
                    code,
                    newPassword
                )
            )

            if (response.isSuccessful) {
                val authResponse = response.body()
                if(authResponse != null)
                    Result.success(authResponse)
                else
                    Result.failure(Exception("Empty body"))
            } else {
                val errorMsg = getErrorMessage(response)
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun resendCode(email: String): Result<ResendCodeResponseDto> {
        return try {
            val response = remoteDataSource.resendCode(
                ResendCodeRequest(email)
            )

            if (response.isSuccessful) {
                val authResponse = response.body()
                if(authResponse != null)
                    Result.success(authResponse)
                else
                    Result.failure(Exception("Empty body"))
            } else {
                val errorMsg = getErrorMessage(response)
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
