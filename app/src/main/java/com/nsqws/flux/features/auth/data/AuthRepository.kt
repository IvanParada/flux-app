package com.nsqws.flux.features.auth.data

import com.nsqws.flux.features.auth.data.remote.AuthRemoteDataSource
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource
) {
    suspend fun registerUser(rut: String): Result<Boolean> {
        return try {
            val response = remoteDataSource.register(rut)
            if (response.isSuccessful) Result.success(true)
            else Result.failure(Exception("User already exists"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loginUser(rut: String): Result<Boolean> {
        return try {
            val response = remoteDataSource.login(rut)
            if (response.isSuccessful) Result.success(true)
            else Result.failure(Exception("Incorrect User"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}