package ram.khab.pogodnick.screens

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ram.khab.pogodnick.R
import ram.khab.pogodnick.model.Screens
import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.screens.WeatherInCities.CityItem
import ram.khab.pogodnick.screens.WeatherInCities.TopBar
import ram.khab.pogodnick.screens.main.MainViewModel
import ram.khab.pogodnick.ui.fontDimensionResource
import ram.khab.pogodnick.ui.theme.*

object WeatherInCities {
    @Composable
    fun WeatherInTheCities(navController: NavController, mainVm: MainViewModel) {
        val state = mainVm.stateLiveData.observeAsState()

        state.value?.let {
            when (it) {
                is MainViewModel.State.Error -> {
                    Toast.makeText(
                        LocalContext.current,
                        stringResource(id = it.errorRes),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        PogodnickTheme {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        navController.navigate(Screens.CityAddScreen)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = stringResource(id = R.string.add_city)
                        )
                    }
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    TopBar()
                    CitiesList(mainVm)
                }
            }
        }
    }

    @Composable
    fun TopBar() {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.weather_in_cities))
            },
            backgroundColor = PurpleBase,
            contentColor = White
        )
    }

    @Composable
    fun CityItem(weather: CardWeather, mainVm: MainViewModel) {
        val iconSize = dimensionResource(R.dimen.icon_size_small)
        val fontSize = fontDimensionResource(R.dimen.text_size)
        val padding = dimensionResource(R.dimen.padding_standart)


        var openDialog by remember {
            mutableStateOf(false)
        }

        val iconHeart =
            if (weather.favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder

        Card(
            shape = Shapes.large,
            backgroundColor = White,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = weather.cityName,
                        fontSize = fontSize,
                        modifier = Modifier.padding(padding)
                    )
                    Text(
                        text = weather.howDegrease,
                        fontSize = fontSize,
                        modifier = Modifier.padding(padding)
                    )
                }
                Divider()
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(padding)
                            .height(iconSize)
                            .width(iconSize)
                            .clickable {
                                mainVm.updateFavoriteWeather(weather.copy(favorite = !weather.favorite))
                            },
                        imageVector = iconHeart,
                        contentDescription = stringResource(id = R.string.like_city),
                        tint = PurpleBase
                    )
                    Image(
                        modifier = Modifier
                            .padding(padding)
                            .height(iconSize)
                            .width(iconSize)
                            .clickable {
                                openDialog = true
                            },
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = stringResource(id = R.string.delete_city),
                    )
                }
            }
        }
        if (openDialog) {
            AlertDialog(
                onDismissRequest = { openDialog = false },
                text = { Text(text = stringResource(id = R.string.delete_city)) },
                confirmButton = {
                    Button(
                        onClick = {
                            mainVm.deleteWeatherCard(weather)
                            openDialog = false
                        }) {
                        Text(stringResource(id = R.string.yes))
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            openDialog = false
                        }) {
                        Text(stringResource(id = R.string.no))
                    }
                }
            )
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun CitiesList(mainVm: MainViewModel) {
        val uiState = mainVm.dataListToUi
        val padding = dimensionResource(id = R.dimen.padding_standart)
        val headerTextSize = fontDimensionResource(id = R.dimen.text_small_size)
        val isRefreshing by mainVm.isRefreshing.collectAsState()
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { mainVm.updateWeather() }) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = padding, vertical = padding),
                verticalArrangement = Arrangement.spacedBy(padding)
            ) {
                uiState.forEach { (initial, contactsForInitial) ->
                    val header = if (initial) R.string.favorites else R.string.other_cities
                    if (uiState.keys.first()) {
                        stickyHeader {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .background(color = PurpleLight)
                            ) {
                                Text(
                                    text = stringResource(header),
                                    Modifier.padding(start = dimensionResource(id = R.dimen.padding_small)),
                                    fontSize = headerTextSize
                                )
                            }
                        }
                    }
                    items(contactsForInitial) { card ->
                        CityItem(weather = card, mainVm)
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun Header() {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val mainVm: MainViewModel? = null
    PogodnickTheme {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(id = R.string.add_city)
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                TopBar()
                val listCard: List<CardWeather> = listOf()
                val padding = dimensionResource(id = R.dimen.padding_standart)
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = padding, vertical = padding),
                    verticalArrangement = Arrangement.spacedBy(padding)
                ) {
                    items(listCard) { card ->
                        CityItem(weather = card, mainVm!!)
                    }
                }
            }
        }
    }
}