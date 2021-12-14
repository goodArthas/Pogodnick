package ram.khab.pogodnick

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.getViewModel
import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.ui.fontDimensionResource
import ram.khab.pogodnick.ui.theme.PogodnickTheme
import ram.khab.pogodnick.ui.theme.PurpleLight
import ram.khab.pogodnick.ui.theme.Shapes
import ram.khab.pogodnick.ui.theme.White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PogodnickTheme {
                Column(Modifier.background(PurpleLight)) {
                    TopBar()
                    CitiesList()
                }
            }
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.weather_in_cities))
    })
}

@Composable
fun CityItem(weather: CardWeather) {
    Card(
        shape = Shapes.large,
        backgroundColor = White,
        modifier = Modifier.fillMaxWidth()
    ) {
        val iconSize = dimensionResource(R.dimen.icon_size_small)
        val fontSize = fontDimensionResource(R.dimen.text_size)
        val padding = dimensionResource(R.dimen.padding_standart)

        var thumbIconLiked by remember {
            mutableStateOf(weather.favorite)
        }

        val iconheart = if (thumbIconLiked) R.drawable.press_heart else R.drawable.heart

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
                Image(
                    modifier = Modifier
                        .padding(padding)
                        .height(iconSize)
                        .width(iconSize)
                        .clickable {
                            thumbIconLiked = !thumbIconLiked
                        },
                    painter = painterResource(id = iconheart),
                    contentDescription = stringResource(id = R.string.like_city)
                )
                Image(
                    modifier = Modifier
                        .padding(padding)
                        .height(iconSize)
                        .width(iconSize)
                        .clickable {

                        },
                    painter = painterResource(id = R.drawable.trash_can),
                    contentDescription = stringResource(id = R.string.delete_city),
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CitiesList() {
    val vm = getViewModel<MainViewModel>()
    val listCard: List<CardWeather> by vm.weatherCardsData.observeAsState(listOf())
    vm.fetch()

    val padding = dimensionResource(id = R.dimen.padding_standart)
    LazyColumn(
        contentPadding = PaddingValues(horizontal = padding, vertical = padding),
        verticalArrangement = Arrangement.spacedBy(padding)
    ) {
        items(listCard) { card ->
            CityItem(weather = card)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PogodnickTheme {
        CitiesList()
    }
}