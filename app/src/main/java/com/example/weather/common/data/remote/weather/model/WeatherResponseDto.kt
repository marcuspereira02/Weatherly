package com.example.weather.common.data.remote.weather.model

import com.google.gson.annotations.SerializedName

data class WeatherResponseDto(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val current: CurrentWeatherDto,
    val hourly: HourlyWeatherDto,
    val daily: DailyWeatherDto
)

data class CurrentWeatherDto(
    @SerializedName("temperature_2m")
    val temperature: Double,
    @SerializedName("apparent_temperature")
    val apparentTemperature: Double,
    @SerializedName("relative_humidity_2m")
    val relativeHumidity: Int,
    @SerializedName("weather_code")
    val weatherCode: Int,
    @SerializedName("wind_speed_10m")
    val windSpeed: Double,
    val precipitation: Double
)

data class HourlyWeatherDto(
    val time: List<String>,
    @SerializedName("temperature_2m")
    val temperature: List<Double>,
    @SerializedName("weather_code")
    val weatherCode: List<Int>,
    @SerializedName("precipitation_probability")
    val precipitationProbability: List<Int>
)

data class DailyWeatherDto(
    val time: List<String>,
    @SerializedName("temperature_2m_max")
    val temperatureMax: List<Double>,
    @SerializedName("temperature_2m_min")
    val temperatureMin: List<Double>,
    @SerializedName("weather_code")
    val weatherCode : List<Int>,
    @SerializedName("precipitation_probability_max")
    val precipitationProbabilityMax: List<Int>
)
