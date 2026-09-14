package com.example.weather.searchScreen.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weather.components.ERSearchBar
import com.example.weather.searchScreen.presentation.SearchViewModel
import kotlin.collections.orEmpty
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import com.example.weather.components.RetryErrorContent

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    navController: NavController
) {
    val locationFound by viewModel.uiLocation.collectAsState()

    var query by rememberSaveable {
        mutableStateOf("")
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchScreenContent(
            onBackClick = { navController.popBackStack() },
            query = query,
            onValueChange = { newQuery ->
                query = newQuery
                viewModel.fetchListLocation(newQuery)
            },
            onSearchClicked = {}
        )

        when {
            locationFound.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }


            locationFound.isError -> {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    RetryErrorContent(
                        locationFound.errorMessage
                    ) {
                        viewModel.tryAgain()
                    }
                }

            }


            else -> {

                LazyColumn {
                    items(locationFound.data.orEmpty()) { location ->

                        ResultList(
                            location = location,
                            onClick = {
                                navController.navigate(
                                    "weatherScreen/${location.latitude}/${location.longitude}/${
                                        Uri.encode(
                                            location.timezone
                                        )
                                    }"
                                )
                            }
                        )
                    }
                }
            }
        }

    }
}

@Composable
private fun SearchScreenContent(
    onBackClick: () -> Unit,
    query: String,
    onValueChange: (String) -> Unit,
    onSearchClicked: () -> Unit,
) {

    Column {
        HeaderScreen(onBackClick = onBackClick)

        Spacer(Modifier.size(16.dp))

        SearchSession(
            query,
            onValueChange,
            onSearchClicked = {
                onSearchClicked.invoke()
            }
        )
    }

}

@Composable
private fun HeaderScreen(
    onBackClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(top = 16.dp, start = 8.dp, end = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow Back"
                )
            }

            Text(
                text = "Weatherly",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
        HorizontalDivider(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            color = Color.LightGray,
            thickness = 1.dp
        )
    }
}

@Composable
private fun SearchSession(
    query: String,
    onValueChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit
) {

    ERSearchBar(
        query,
        placeHolder = "Find the location you want",
        onValueChange,
        onSearchClicked = { onSearchClicked.invoke(query) }
    )
}

@Composable
private fun ResultList(location: SearchUiData, onClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(
            text = location.name,
            fontSize = 16.sp
        )

        Text(
            text = "${location.admin1}, ${location.country}",
            fontSize = 14.sp
        )
    }

}
