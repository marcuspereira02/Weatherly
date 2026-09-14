package com.example.weather.searchScreen.presentation.ui

data class SearchUiState(
    val isLoading: Boolean = false,
    val data: List<SearchUiData>? = emptyList(),
    val isOffline: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "Something went error."
)

data class SearchUiData(
    val id : Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val timezone : String,
    val country : String,
    val admin1: String
)