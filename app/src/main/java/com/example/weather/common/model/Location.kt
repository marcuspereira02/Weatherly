package com.example.weather.common.model

data class Location(
    val id : Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val timezone : String,
    val country : String,
    val admin1 : String
)