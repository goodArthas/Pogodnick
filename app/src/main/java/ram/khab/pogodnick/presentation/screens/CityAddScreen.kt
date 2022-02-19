package ram.khab.pogodnick.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import ram.khab.pogodnick.R
import ram.khab.pogodnick.presentation.screens.main.MainViewModel
import ram.khab.pogodnick.presentation.ui.fontDimensionResource
import ram.khab.pogodnick.presentation.ui.theme.BlackText
import ram.khab.pogodnick.presentation.ui.theme.PogodnickTheme
import ram.khab.pogodnick.presentation.ui.theme.Shapes
import ram.khab.pogodnick.ui.theme.MyAppBar

const val CITY_ADD_SCREEN_NAME = "cityAddScreen"

class CityAddScreen {

    /*@Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun PreviewScreen() {
        Screen(MainViewModel(
            object : Repository {
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

            },
        )) {

        }
    }*/

    @Composable
    fun Screen(
        mainViewModel: MainViewModel,
        navigationBack: () -> Unit
    ) {
        val padding = dimensionResource(id = R.dimen.padding_standard)
        val mediumPadding = dimensionResource(id = R.dimen.padding_large)
        val buttonTextSize = fontDimensionResource(id = R.dimen.button_text_size)
        val buttonHeightSize = dimensionResource(id = R.dimen.button_height_size)
        var text by rememberSaveable { mutableStateOf("") }

        PogodnickTheme {
            Column(Modifier.background(MaterialTheme.colors.background)) {
                MyAppBar(stringResource(id = R.string.add_city)) {
                    navigationBack()
                }
                InputText {
                    text = it
                }
                Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Bottom) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Button(
                            onClick = {
                                mainViewModel.saveCity(text)
                                navigationBack()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = padding, end = padding, bottom = mediumPadding)
                                .height(buttonHeightSize),
                            enabled = text.length > 2
                        )
                        {
                            Text(
                                text = stringResource(id = R.string.add),
                                fontSize = buttonTextSize
                            )
                        }
                    }
                }
            }
        }
    }


    @Composable
    private fun InputText(changeTest: (String) -> Unit) {
        val padding = dimensionResource(id = R.dimen.padding_standard)
        val mediumPadding = dimensionResource(id = R.dimen.padding_medium)
        var text by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            shape = Shapes.large,
            value = text,
            onValueChange = {
                text = it
                changeTest(it)
            },
            textStyle = TextStyle(color = BlackText),
            modifier = Modifier
                .padding(horizontal = padding)
                .padding(top = mediumPadding)
                .fillMaxWidth(),
            singleLine = true,
            label = { Text(stringResource(id = R.string.enter_city_name)) }
        )
    }

}


