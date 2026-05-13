package com.nsqws.flux.features.profile.data.repository
import com.nsqws.flux.features.profile.data.remote.dto.response.BankConstantsResponse
import com.nsqws.flux.features.profile.data.remote.datasource.ProfileRemoteDataSource
import com.nsqws.flux.features.profile.data.remote.dto.request.UpdateBankDataDto
import com.nsqws.flux.features.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProfileRemoteDataSource
) : ProfileRepository {

    override suspend fun getBankConstants(): Result<BankConstantsResponse> {
        return try {
            val response = remoteDataSource.getBankConstants()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                val errorMsg = getErrorMessage(response)
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateBankData(
        bankHolderId: String,
        bankNumber: String,
        bankType: String,
        bankInstitutionId: String
    ): Result<Unit> {
        return try {
            val response = remoteDataSource.updateBankData(
                UpdateBankDataDto(
                    bankHolderId = bankHolderId,
                    bankNumber = bankNumber,
                    bankType = bankType,
                    bankInstitutionId = bankInstitutionId
                )
            )
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception(getErrorMessage(response)))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun getErrorMessage(response: retrofit2.Response<*>): String {
        return try {
            val errorJson = response.errorBody()?.string()
            if (!errorJson.isNullOrBlank()) {
                val jsonObject = org.json.JSONObject(errorJson)
                jsonObject.optString("message", "Error desconocido (${response.code()})")
            } else {
                "Error: ${response.code()}"
            }
        } catch (e: Exception) {
            "Error inesperado: ${e.localizedMessage}"
        }
    }
}
