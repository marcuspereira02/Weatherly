package com.example.weather.common.data.remote.search

import android.accounts.NetworkErrorException
import com.example.weather.common.model.Location

class SearchRemoteDataSource(
    private val service: GeocodingService,
) {
    suspend fun getLocationResults(name: String): Result<List<Location>?> {
        return try {
            val response = service.getListResult(name)
            if (response.isSuccessful) {
                val location = response.body()?.results?.map {
                    Location(
                        id = it.id,
                        name = it.name,
                        longitude = it.longitude,
                        latitude = it.latitude,
                        timezone = it.timezone,
                        country = it.country,
                        admin1 = it.admin1
                    )
                }
                Result.success(location)
            } else {
                Result.failure(NetworkErrorException(response.message()))
            }

        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        }
    }
}