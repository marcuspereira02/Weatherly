package com.example.weather.common.data.remote.weather

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL =
    "https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&timezone=Europe%2FBerlin&daily=temperature_2m_max,temperature_2m_min,weather_code,precipitation_probability_max&hourly=temperature_2m,weather_code,precipitation_probability&current=temperature_2m,apparent_temperature,relative_humidity_2m,weather_code,wind_speed_10m,precipitation"

object WeatherRetrofitClient {

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    val retrofitInstanceWeather: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()

}