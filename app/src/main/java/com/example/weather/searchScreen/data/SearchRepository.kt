package com.example.weather.searchScreen.data

import com.example.weather.common.data.remote.search.SearchRemoteDataSource
import com.example.weather.common.model.Location

class SearchRepository(
    private val remote: SearchRemoteDataSource
) {
    suspend fun getSearchList(name: String): Result<List<Location>?> {
        return try {
            val result = remote.getLocationResults(name)
            if (result.isSuccess) {
                val locationRemote = result.getOrNull()
                Result.success(locationRemote)
            } else {
                 result
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        }
    }
}