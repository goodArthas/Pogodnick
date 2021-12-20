package ram.khab.pogodnick.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import ram.khab.pogodnick.R
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
            }
        }
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun ScreenPre(
    ) {
        Screen(cityName = "Москва")
    }

}