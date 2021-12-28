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
import coil.compose.rememberImagePainter
import org.koin.androidx.compose.getViewModel
import ram.khab.pogodnick.R

import ram.khab.pogodnick.ui.fontDimensionResource
import ram.khab.pogodnick.ui.theme.Black
import ram.khab.pogodnick.ui.theme.BlackText
import ram.khab.pogodnick.ui.theme.MyAppBar
import ram.khab.pogodnick.ui.theme.PogodnickTheme

const val WEATHER_DETAIL_SCREEN_NAME = "weatherDetailScreen"

class WeatherDetailScreen {

    @Composable
    fun Screen(
        cityName: String?,
        backArrowClick: () -> Unit
    ) {
        val mainViewModel = getViewModel<WeatherDetailViewModel>()
        val weatherDetail = mainViewModel.dataToUi
        mainViewModel.getDetailWeather(cityName ?: "Москва")
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
                    fontSize = fontDimensionResource(id = R.dimen.text_smaller_size),
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
                        painter = rememberImagePainter(
                            "https://openweathermap.org/img/wn/${weatherDetail.weatherHeaderIconUrl}.png"
                        ),
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
                    fontSize = fontDimensionResource(id = R.dimen.text_small_size)
                )
                Text(
                    text = stringResource(id = R.string.forecast_on_3_days),
                    modifier = Modifier
                        .padding(top = paddingSmall)
                        .fillMaxWidth(),
                    color = BlackText,
                    fontSize = fontDimensionResource(id = R.dimen.text_small_size),
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier
                        .padding(top = paddingSmall)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(paddingStandard)) {
                        itemsIndexed(weatherDetail.weathersList) { count, item ->
                            ColumnDay(
                                dateText = item.dateText,
                                iconUrlText = item.iconUrlText,
                                iconUrlDescription = item.iconUrlDescription,
                                temperatureText = item.temperatureText,
                                isStartPadding = count != 0,
                                isEndPadding = count != weatherDetail.weathersList.size - 1
                            )
                        }

                    }
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
    private fun WeatherLine(headerText: String, bodyText: String) {
        val paddingStandard = dimensionResource(id = R.dimen.padding_standard)
        val textHeadSize = fontDimensionResource(id = R.dimen.text_smaller_size)
        val textBodySize = fontDimensionResource(id = R.dimen.text_small_size)
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
        temperatureText: String,
        isStartPadding: Boolean,
        isEndPadding: Boolean
    ) {
        val paddingStandard = dimensionResource(id = R.dimen.padding_standard)
        val icon3daySize = dimensionResource(id = R.dimen.icon_size_medium)
        Column(
            modifier = Modifier.padding(
                start = if (isStartPadding) paddingStandard else 0.dp,
                end = if (isEndPadding) paddingStandard else 0.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = dateText)
            Image(
                painter = rememberImagePainter("https://openweathermap.org/img/wn/${iconUrlText}.png"),
                contentDescription = iconUrlDescription,
                Modifier.size(icon3daySize)
            )
            val temperature = "${temperatureText} ${stringResource(id = R.string.celsius)}"
            Text(text = temperature)
        }
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun Prew() {
        Screen(cityName = "") {

        }
    }
}
