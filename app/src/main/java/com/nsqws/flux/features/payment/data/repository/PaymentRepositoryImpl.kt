package com.nsqws.flux.features.payment.data.repository
import com.nsqws.flux.core.data.local.TokenManager
import com.nsqws.flux.features.payment.data.remote.api.PaymentApi
import com.nsqws.flux.features.payment.data.remote.dto.request.PaymentRequestDto
import com.nsqws.flux.features.payment.domain.repository.PaymentRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import com.nsqws.flux.features.payment.domain.model.PaymentLink

class PaymentRepositoryImpl @Inject constructor(
    private val api: PaymentApi,
    private val tokenManager: TokenManager
) : PaymentRepository {

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

    override suspend fun createPaymentLink(amount: Int, description: String): Result<PaymentLink> {
        return try {
            val userId = tokenManager.userId.first() ?: return Result.failure(Exception("No user id"))

            val response = api.createPayment(
                PaymentRequestDto(
                    userId = userId,
                    amount = amount,
                    description = description
                )
            )

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(
                        PaymentLink(
                            url = body.url,
                            reference = body.reference
                        )
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
