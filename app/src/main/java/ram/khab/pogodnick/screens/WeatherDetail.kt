package ram.khab.pogodnick.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ram.khab.pogodnick.R
import ram.khab.pogodnick.model.pojo.WeatherListByDays
import ram.khab.pogodnick.ui.fontDimensionResource
import ram.khab.pogodnick.ui.theme.BlackText
import ram.khab.pogodnick.ui.theme.MyAppBar
import ram.khab.pogodnick.ui.theme.PogodnickTheme

const val WEATHER_DETAIL_SCREEN_NAME = "weatherDetailScreen"

class WeatherDetail {

    @Composable
    fun Screen(cityName: String) {
        PogodnickTheme {
            Column {
                MyAppBar(cityName)
                val paddingStandard = dimensionResource(id = R.dimen.padding_standard)
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
                        imageVector = Icons.Default.Settings,
                        contentDescription = null,
                        Modifier.size(
                            dimensionResource(id = R.dimen.icon_size_large)
                        )
                    )
                    Text(
                        text = " +2 °C ",
                        fontSize = fontDimensionResource(
                            id = R.dimen.text_medium_size
                        )
                    )

                }
                Text(
                    text = "Ощущается как 0 °C. Пасмурно",
                    modifier = Modifier.padding(
                        start = paddingStandard, top = dimensionResource(
                            id = R.dimen.padding_small
                        )
                    ),
                    color = BlackText,
                    fontSize = fontDimensionResource(id = R.dimen.text_small_size)
                )
                Text(
                    text = "Прогноз на 3 дня",
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.padding_small))
                        .fillMaxWidth(),
                    color = BlackText,
                    fontSize = fontDimensionResource(id = R.dimen.text_small_size),
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.padding_small))
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    val icon3daySize = dimensionResource(id = R.dimen.icon_size_medium)
                    val list = listOf(
                        WeatherListByDays("30.10", "", "+1  C"),
                        WeatherListByDays("30.10", "", "+1  C"),
                        WeatherListByDays("30.10", "", "+1  C")
                    )
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(paddingStandard)) {
                        itemsIndexed(list) { _, item ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = item.dateText)
                                Image(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = null,
                                    Modifier.size(icon3daySize)
                                )
                                Text(text = item.temperatureText)
                            }

                        }
                    }
                }

                DividerHorizontal()
            }
        }
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
    private fun Column3Day(
        dateText: String,
        iconUrlText: String,
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
            Text(text = "30.11")
            Image(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                Modifier.size(icon3daySize)
            )
            Text(text = "+1 °C")
        }
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun ScreenPre(
    ) {
        Screen(cityName = "Москва")
    }

}