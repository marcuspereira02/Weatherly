package com.example.weather

import android.app.Application
import com.example.weather.common.data.remote.search.GeocodingRetrofitClient
import com.example.weather.common.data.remote.search.GeocodingService
import com.example.weather.common.data.remote.search.SearchRemoteDataSource
import com.example.weather.common.data.remote.weather.WeatherRetrofitClient
import com.example.weather.common.data.remote.weather.WeatherService
import com.example.weather.searchScreen.data.SearchRepository

class WeatherlyApplication : Application() {

    private val geocodingService by lazy {
        GeocodingRetrofitClient.retrofitInstanceGeocoding.create(GeocodingService::class.java)
    }

    private val searchRemoteDataSource by lazy {
        SearchRemoteDataSource(geocodingService)
    }

    val searchRepository by lazy {
        SearchRepository(searchRemoteDataSource)
    }

    private val weatherService by lazy {
        WeatherRetrofitClient.retrofitInstanceWeather.create(WeatherService::class.java)
    }





}