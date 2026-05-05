package com.nsqws.flux.features.profile.data.repository
import com.nsqws.flux.features.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
) : ProfileRepository {

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


}
