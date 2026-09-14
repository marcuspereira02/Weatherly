package com.example.weather.common.data.remote.search.model

data class GeocodingResultDto(
    val id : Int,
    val name : String,
    val latitude : Double,
    val longitude : Double,
    val timezone : String,
    val country : String,
    val admin1: String
)

data class GeocodingResponseDto(
    val results : List<GeocodingResultDto>
)

