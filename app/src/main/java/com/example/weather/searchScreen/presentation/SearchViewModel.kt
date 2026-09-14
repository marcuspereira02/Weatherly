package com.example.weather.searchScreen.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.weather.WeatherlyApplication
import com.example.weather.searchScreen.data.SearchRepository
import com.example.weather.searchScreen.presentation.ui.SearchUiData
import com.example.weather.searchScreen.presentation.ui.SearchUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class SearchViewModel(
    private val repository: SearchRepository
) : ViewModel() {

    private val _uiLocation = MutableStateFlow(SearchUiState())
    val uiLocation: StateFlow<SearchUiState> = _uiLocation.asStateFlow()

    private var lastQuery = ""

    fun fetchListLocation(nameLocation: String) {

        lastQuery = nameLocation

        viewModelScope.launch(Dispatchers.IO) {
            _uiLocation.value = SearchUiState(isLoading = true)
            val result = repository.getSearchList(nameLocation)
            if (result.isSuccess) {
                val locations = result.getOrNull()
                val locationsUiData = locations?.map {
                    SearchUiData(
                        id = it.id,
                        name = it.name,
                        latitude = it.latitude,
                        longitude = it.longitude,
                        timezone = it.timezone,
                        country = it.country,
                        admin1 = it.admin1
                    )
                }
                _uiLocation.value = SearchUiState(data = locationsUiData)

            } else {
                val ex = result.exceptionOrNull()
                if (ex is UnknownHostException) {
                    _uiLocation.value = SearchUiState(
                        isOffline = true,
                        isError = true,
                        errorMessage = "Not internet connection"
                    )
                } else {
                    _uiLocation.value = SearchUiState(isError = true)
                }
            }
        }
    }

    fun tryAgain() {
        fetchListLocation(lastQuery)
    }


    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return SearchViewModel(
                    repository = (application as WeatherlyApplication).searchRepository
                ) as T
            }
        }
    }
}