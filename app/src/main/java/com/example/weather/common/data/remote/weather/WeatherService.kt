package com.example.weather.common.data.remote.weather

import com.example.weather.common.data.remote.weather.model.WeatherResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("timezone") timezone: String
    ): Response<WeatherResponseDto>

}