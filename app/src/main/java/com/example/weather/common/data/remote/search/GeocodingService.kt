package com.example.weather.common.data.remote.search

import com.example.weather.common.data.remote.search.model.GeocodingResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingService {

    @GET("v1/search")
    suspend fun getListResult(
        @Query("name") name : String
    ): Response<GeocodingResponseDto>
}