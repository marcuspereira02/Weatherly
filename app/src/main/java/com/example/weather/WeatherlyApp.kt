package com.example.weather

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weather.searchScreen.presentation.ui.SearchScreen
import com.example.weather.searchScreen.presentation.SearchViewModel
import com.example.weather.weatherScreen.presentation.ui.WeatherScreen

@Composable
fun WeatherlyApp(searchViewModel: SearchViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "weatherScreen/0.0/0.0/America%2FSao_Paulo"
    ) {
        composable(route = "weatherScreen/{latitude}/{longitude}/{timezone}") { backStackEntry ->
            val latitude =
                backStackEntry.arguments?.getString("latitude")?.toDoubleOrNull() ?: 0.0

            val longitude =
                backStackEntry.arguments?.getString("longitude")?.toDoubleOrNull() ?: 0.0

            val timezone = backStackEntry.arguments?.getString("timezone").orEmpty()
            WeatherScreen(
                navController = navController,
                latitude = latitude,
                longitude = longitude,
                timezone = timezone
            )
        }
        composable(route = "searchScreen") {
            SearchScreen(viewModel = searchViewModel, navController = navController)
        }
    }
}