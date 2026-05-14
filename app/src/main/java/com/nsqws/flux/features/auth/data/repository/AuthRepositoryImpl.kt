package com.nsqws.flux.features.auth.data.repository

import android.util.Log
import com.nsqws.flux.core.data.local.TokenManager
import com.nsqws.flux.features.auth.data.remote.datasource.AuthRemoteDataSource
import com.nsqws.flux.features.auth.data.remote.dto.request.ForgotPasswordRequest
import com.nsqws.flux.features.auth.data.remote.dto.request.LoginRequest
import com.nsqws.flux.features.auth.data.remote.dto.request.RegisterRequest
import com.nsqws.flux.features.auth.data.remote.dto.request.ResendCodeRequest
import com.nsqws.flux.features.auth.data.remote.dto.request.ResetPasswordRequest
import com.nsqws.flux.features.auth.data.remote.dto.request.VerifyRequest
import com.nsqws.flux.features.auth.data.remote.dto.request.VerifyResetCodeRequest
import com.nsqws.flux.features.auth.domain.model.AuthMessage
import com.nsqws.flux.features.auth.domain.model.AuthSession
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

    override suspend fun register(
        email: String,
        password: String,
        rut: String
    ): Result<AuthMessage> {
        return try {
            val response = remoteDataSource.register(
                RegisterRequest(email, password, rut)
            )

            if (response.isSuccessful) {
                val authResponse = response.body()

                if (authResponse != null) {
                    Result.success(
                        AuthMessage(message = authResponse.message)
                    )
                } else {
                    Result.failure(Exception("Empty body"))
                }
            } else {
                Result.failure(Exception(getErrorMessage(response)))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<AuthSession> {
        return try {
            val response = remoteDataSource.login(
                LoginRequest(email, password)
            )

            if (response.isSuccessful) {
                val authResponse = response.body()

                if (authResponse != null) {
                    tokenManager.saveSession(
                        token = authResponse.accessToken,
                        userId = authResponse.user.id,
                        userRut = authResponse.user.rut
                    )
                    Log.d("LoginDataUser", "${authResponse.user}")
                    Result.success(
                        AuthSession(
                            accessToken = authResponse.accessToken,
                            userId = authResponse.user.id,
                            userRut = authResponse.user.rut
                        )
                    )
                } else {
                    Result.failure(Exception("Respuesta vacía"))
                }
            } else {
                Result.failure(Exception(getErrorMessage(response)))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun verify(
        email: String,
        code: String
    ): Result<AuthMessage> {
        return try {
            val response = remoteDataSource.verify(
                VerifyRequest(email, code)
            )

            if (response.isSuccessful) {
                val authResponse = response.body()

                if (authResponse != null) {
                    Result.success(
                        AuthMessage(message = authResponse.message)
                    )
                } else {
                    Result.failure(Exception("Empty body"))
                }
            } else {
                Result.failure(Exception(getErrorMessage(response)))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun forgotPassword(
        email: String
    ): Result<AuthMessage> {
        return try {
            val response = remoteDataSource.forgotPassword(
                ForgotPasswordRequest(email)
            )

            if (response.isSuccessful) {
                val authResponse = response.body()

                if (authResponse != null) {
                    Result.success(
                        AuthMessage(message = authResponse.message)
                    )
                } else {
                    Result.failure(Exception("Empty body"))
                }
            } else {
                Result.failure(Exception(getErrorMessage(response)))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun verifyResetCode(
        email: String,
        code: String
    ): Result<AuthMessage> {
        return try {
            val response = remoteDataSource.verifyResetCode(
                VerifyResetCodeRequest(email, code)
            )

            if (response.isSuccessful) {
                val authResponse = response.body()

                if (authResponse != null) {
                    Result.success(
                        AuthMessage(message = authResponse.message)
                    )
                } else {
                    Result.failure(Exception("Empty body"))
                }
            } else {
                Result.failure(Exception(getErrorMessage(response)))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun resetPassword(
        email: String,
        code: String,
        newPassword: String
    ): Result<AuthMessage> {
        return try {
            val response = remoteDataSource.resetPassword(
                ResetPasswordRequest(email, code, newPassword)
            )

            if (response.isSuccessful) {
                val authResponse = response.body()

                if (authResponse != null) {
                    Result.success(
                        AuthMessage(message = authResponse.message)
                    )
                } else {
                    Result.failure(Exception("Empty body"))
                }
            } else {
                Result.failure(Exception(getErrorMessage(response)))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun resendCode(
        email: String
    ): Result<AuthMessage> {
        return try {
            val response = remoteDataSource.resendCode(
                ResendCodeRequest(email)
            )

            if (response.isSuccessful) {
                val authResponse = response.body()

                if (authResponse != null) {
                    Result.success(
                        AuthMessage(message = authResponse.message)
                    )
                } else {
                    Result.failure(Exception("Empty body"))
                }
            } else {
                Result.failure(Exception(getErrorMessage(response)))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}