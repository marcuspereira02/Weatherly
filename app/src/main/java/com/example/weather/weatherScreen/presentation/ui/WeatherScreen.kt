package com.example.weather.weatherScreen.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.weather.R

@Composable
fun WeatherScreen(
    navController: NavHostController,
    latitude: Double,
    longitude: Double,
    timezone: String
) {
    WeatherScreenContent(onSearchClicked = { navController.navigate(route = "searchScreen") })


}

@Composable
private fun WeatherScreenContent(
    onSearchClicked: () -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {
        HeaderWeather(onSearchClick = onSearchClicked)
    }

}

@Composable
private fun HeaderWeather(
    onSearchClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(id = R.drawable.weather_icon),
                contentDescription = "icon of a sun with a cloud",
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                text = "Weatherly"
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search city"
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            color = Color.LightGray,
            thickness = 1.dp
        )
    }
}

@Composable
@Preview
private fun WeatherScreenPreview(modifier: Modifier = Modifier) {
    WeatherScreenContent {

    }
}