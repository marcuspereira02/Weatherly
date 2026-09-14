package com.example.weather.common.data.remote.weather

import android.accounts.NetworkErrorException
import com.example.weather.common.model.CurrentWeather
import com.example.weather.common.model.DailyForecast
import com.example.weather.common.model.HourlyForecast
import com.example.weather.common.model.Weather

class WeatherRemoteDataSource (
    private val service: WeatherService
){
    suspend fun getWeatherResult(
        latitude: Double,
        longitude: Double,
        timezone: String
    ): Result<Weather> {
        return try {
            val response = service.getWeather(latitude, longitude, timezone)
            if (response.isSuccessful) {
                val weather = response.body()
                if (weather != null) {
                    val weatherData = Weather(
                        latitude = weather.latitude,
                        longitude = weather.longitude,
                        timezone = weather.timezone,
                        current = CurrentWeather(
                            temperature = weather.current.temperature,
                            apparentTemperature = weather.current.apparentTemperature,
                            relativeHumidity = weather.current.relativeHumidity,
                            weatherCode = weather.current.weatherCode,
                            windSpeed = weather.current.windSpeed,
                            precipitation = weather.current.precipitation
                        ),
                        hourly = HourlyForecast(
                            time = weather.hourly.time,
                            temperature = weather.hourly.temperature,
                            weatherCode = weather.hourly.weatherCode,
                            precipitationProbability = weather.hourly.precipitationProbability
                        ),
                        daily = DailyForecast(
                            time = weather.daily.time,
                            temperatureMax = weather.daily.temperatureMax,
                            temperatureMin = weather.daily.temperatureMin,
                            weatherCode = weather.daily.weatherCode,
                            precipitationProbabilityMax = weather.daily.precipitationProbabilityMax
                        )

                    )
                    Result.success(weatherData)
                } else{
                    Result.failure(UnknownError("Response body is empty"))
                }
            } else {
                Result.failure(NetworkErrorException(response.message()))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        }
    }
}