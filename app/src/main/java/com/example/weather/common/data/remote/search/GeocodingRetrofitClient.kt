package com.example.weather.common.data.remote.search

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://geocoding-api.open-meteo.com/"

object GeocodingRetrofitClient {

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    val retrofitInstanceGeocoding: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}