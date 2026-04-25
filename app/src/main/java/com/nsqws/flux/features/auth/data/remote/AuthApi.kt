package com.nsqws.flux.features.auth.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body body: Map<String, String>): Response<Unit>

    @POST("auth/register")
    suspend fun register(@Body body: Map<String, String>): Response<Unit>
}