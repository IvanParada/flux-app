package com.nsqws.flux.features.auth.data.repository

import com.nsqws.flux.features.auth.data.datasource.AuthRemoteDataSource
import com.nsqws.flux.features.auth.data.models.LoginRequest
import com.nsqws.flux.features.auth.data.models.RegisterRequest
import com.nsqws.flux.features.auth.data.models.VerifyRequest
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource
) : IAuthRepository {

    override suspend fun register(email: String, password: String, rut: String): Result<Unit> {
        return try {
            val response = remoteDataSource.register(RegisterRequest(email, password, rut))
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                val msg = if (response.code() == 409) "El usuario ya existe" else "Error: ${response.code()}"
                Result.failure(Exception(msg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            val response = remoteDataSource.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Credenciales incorrectas"))
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
                Result.failure(Exception("Código incorrecto"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
