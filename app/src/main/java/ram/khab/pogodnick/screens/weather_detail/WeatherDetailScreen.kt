package ram.khab.pogodnick.screens.weather_detail


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.getViewModel
import ram.khab.pogodnick.R
import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.model.pojo.WeatherByDays
import ram.khab.pogodnick.model.pojo.WeatherDetails
import ram.khab.pogodnick.model.repository.Repository
import ram.khab.pogodnick.ui.fontDimensionResource
import ram.khab.pogodnick.ui.theme.*

const val WEATHER_DETAIL_SCREEN_NAME = "weatherDetailScreen"

class WeatherDetailScreen {

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun PreviewScreen() {
        Screen(
            "Mockva", WeatherDetailViewModel(object : Repository {
                override fun getWeather(cardWeather: CardWeather): Flow<CardWeather> {
                    TODO("Not yet implemented")
                }

                override fun getWeatherDetails(cityName: String): Flow<WeatherDetails> {
                    TODO("Not yet implemented")
                }

                override fun getAllWeather(): Flow<List<CardWeather>> {
                    TODO("Not yet implemented")
                }

                override fun deleteWeather(city: CardWeather): Flow<Int> {
                    TODO("Not yet implemented")
                }

                override fun saveCity(cardWeather: CardWeather): Flow<Long> {
                    TODO("Not yet implemented")
                }

                override fun updateWeather(cityCardWeatherList: List<CardWeather>): Flow<Int> {
                    TODO("Not yet implemented")
                }

            })
        ) {

        }
    }

    @Preview()
    @Composable
    fun PreviewLazyRow() {
        RowListWeather(
            weatherDetail = WeatherDetails(
                weathersList = listOf(
                    WeatherByDays(
                        "20.10", "", "", "-10"
                    ), WeatherByDays(
                        "21.10", "", "", "-12"
                    ), WeatherByDays(
                        "22.10", "", "", "-13"
                    )
                )
            )
        )
    }

    @Composable
    fun Screen(
        cityName: String?,
        viewModel: WeatherDetailViewModel = getViewModel(),
        backArrowClick: () -> Unit
    ) {
        val weatherDetail = viewModel.dataToUi
        viewModel.getDetailWeather(cityName ?: "Москва")
        PogodnickTheme {
            Column {
                MyAppBar(cityName ?: "Москва") {
                    backArrowClick()
                }
                val paddingStandard = dimensionResource(id = R.dimen.padding_standard)
                val paddingSmall = dimensionResource(id = R.dimen.padding_small)
                Text(
                    text = stringResource(id = R.string.temperature),
                    color = BlackText,
                    fontSize = fontDimensionResource(id = R.dimen.text_normal_size),
                    modifier = Modifier.padding(
                        start = paddingStandard,
                        top = paddingStandard
                    )
                )
                Row(
                    modifier = Modifier.padding(start = paddingStandard),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberImagePainter(getWeatherIconUrl(weatherDetail.weatherHeaderIconUrl)),
                        contentDescription = weatherDetail.weatherHeaderIconDescription,
                        Modifier.size(
                            dimensionResource(id = R.dimen.icon_size_large)
                        )
                    )
                    Text(
                        text = weatherDetail.temperatureHeader,
                        fontSize = fontDimensionResource(id = R.dimen.text_medium_size)
                    )

                }
                val feelsLike = "${stringResource(id = R.string.feels_like)} " +
                        "${weatherDetail.feelsLikeTemperature} " +
                        "${stringResource(id = R.string.celsius)} " +
                        weatherDetail.weather
                Text(
                    text = feelsLike,
                    modifier = Modifier.padding(
                        start = paddingStandard, top = paddingSmall
                    ),
                    color = BlackText,
                    fontSize = fontDimensionResource(id = R.dimen.text_normal_size)
                )
                Text(
                    text = stringResource(id = R.string.forecast_on_3_days),
                    modifier = Modifier
                        .padding(top = paddingSmall)
                        .fillMaxWidth(),
                    color = BlackText,
                    fontSize = fontDimensionResource(id = R.dimen.text_normal_size),
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier
                        .padding(top = paddingSmall)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    RowListWeather(weatherDetail)
                }
                DividerHorizontal()
                val visibilityRange =
                    "${weatherDetail.visibilityRange} ${stringResource(id = R.string.metre)}"
                WeatherLine(
                    headerText = stringResource(id = R.string.visibility),
                    bodyText = visibilityRange
                )
                DividerHorizontal()
                val pressure = "${weatherDetail.pressure} ${stringResource(id = R.string.kPa)}"
                WeatherLine(
                    headerText = stringResource(id = R.string.pressure),
                    bodyText = pressure
                )
                DividerHorizontal()
                val humidity = "${weatherDetail.humidity} ${stringResource(id = R.string.percent)}"
                WeatherLine(
                    headerText = stringResource(id = R.string.humidity),
                    bodyText = humidity
                )
                DividerHorizontal()
                val windText =
                    "${weatherDetail.windSpeed} ${stringResource(id = R.string.metreInSec)} (${weatherDetail.windDirection})"
                WeatherLine(
                    headerText = stringResource(id = R.string.wind),
                    bodyText = windText
                )
            }
        }
    }

    @Composable
    private fun RowListWeather(weatherDetail: WeatherDetails) {
        LazyRow() {
            itemsIndexed(weatherDetail.weathersList) { count, item ->
                ConstraintLayout {
                    val row = createRef()
                    Row(modifier = Modifier
                        .height(IntrinsicSize.Min)
                        .constrainAs(row) {
                            top.linkTo(parent.top)
                        }) {
                        ColumnDay(
                            dateText = item.dateText,
                            iconUrlText = item.iconUrlText,
                            iconUrlDescription = item.iconUrlDescription,
                            temperatureText = item.temperatureText
                        )
                        if (count < weatherDetail.weathersList.lastIndex)
                            Spacer(modifier = Modifier.width(8.dp))
                        if (count < weatherDetail.weathersList.lastIndex) {
                            Divider(
                                color = DividerColor,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun WeatherLine(headerText: String, bodyText: String) {
        val paddingStandard = dimensionResource(id = R.dimen.padding_standard)
        val textHeadSize = fontDimensionResource(id = R.dimen.text_small_medium_size)
        val textBodySize = fontDimensionResource(id = R.dimen.text_normal_size)
        Text(
            fontSize = textHeadSize,
            color = BlackText,
            text = headerText,
            modifier = Modifier.padding(start = paddingStandard)
        )
        Text(
            fontSize = textBodySize,
            color = Black,
            text = bodyText,
            modifier = Modifier.padding(start = paddingStandard)
        )
    }

    @Composable
    private fun DividerHorizontal() {
        Spacer(modifier = Modifier.height(16.dp))
        Divider(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .fillMaxWidth()
        )
    }

    @Composable
    private fun ColumnDay(
        dateText: String,
        iconUrlText: String,
        iconUrlDescription: String,
        temperatureText: String
    ) {
        val icon3daySize = dimensionResource(id = R.dimen.icon_size_large)
        val textSize = fontDimensionResource(id = R.dimen.text_normal_size)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = dateText, fontSize = textSize)
            Image(
                painter = rememberImagePainter(getWeatherIconUrl(iconUrlText)),
                contentDescription = iconUrlDescription,
                Modifier.size(icon3daySize)
            )
            val temperature = "${temperatureText} ${stringResource(id = R.string.celsius)}"
            Text(text = temperature, fontSize = textSize)
        }
    }

    private fun getWeatherIconUrl(iconId: String) =
        "https://openweathermap.org/img/wn/${iconId}.png"
}
