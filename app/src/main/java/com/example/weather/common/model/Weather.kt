package com.example.weather.common.model

data class Weather(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val current: CurrentWeather,
    val hourly: HourlyForecast,
    val daily: DailyForecast
)

data class CurrentWeather(
    val temperature: Double,
    val apparentTemperature: Double,
    val relativeHumidity: Int,
    val weatherCode: Int,
    val windSpeed: Double,
    val precipitation: Double
)

data class HourlyForecast(
    val time: List<String>,
    val temperature: List<Double>,
    val weatherCode: List<Int>,
    val precipitationProbability: List<Int>
)

data class DailyForecast(
    val time: List<String>,
    val temperatureMax: List<Double>,
    val temperatureMin: List<Double>,
    val weatherCode : List<Int>,
    val precipitationProbabilityMax: List<Int>
)

