package com.nsqws.flux.core.data.network

import com.nsqws.flux.core.data.local.TokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { tokenManager.token.first() }

        val request = chain.request().newBuilder()

        token?.let {
            android.util.Log.d("AUTH_DEBUG", "Token enviado: Bearer $it")
            request.addHeader("Authorization", "Bearer $it")
        } ?: android.util.Log.e("AUTH_DEBUG", "¡OJO! El token es NULO en el interceptor")

        return chain.proceed(request.build())
    }
}