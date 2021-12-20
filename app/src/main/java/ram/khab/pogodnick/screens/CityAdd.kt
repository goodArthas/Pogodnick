package ram.khab.pogodnick.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
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
import androidx.navigation.NavController
import ram.khab.pogodnick.R
import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.screens.main.MainViewModel
import ram.khab.pogodnick.ui.fontDimensionResource
import ram.khab.pogodnick.ui.theme.BlackText
import ram.khab.pogodnick.ui.theme.MyAppBar
import ram.khab.pogodnick.ui.theme.PogodnickTheme
import ram.khab.pogodnick.ui.theme.Shapes

const val CITY_ADD_SCREEN_NAME = "cityAddScreen"

class CityAddScreen {
    @Composable
    fun Screen(
        navController: NavController,
        mainViewModel: MainViewModel
    ) {
        val padding = dimensionResource(id = R.dimen.padding_standard)
        val mediumPadding = dimensionResource(id = R.dimen.padding_large)
        val buttonTextSize = fontDimensionResource(id = R.dimen.button_text_size)
        val buttonHeightSize = dimensionResource(id = R.dimen.button_height_size)
        var text by rememberSaveable { mutableStateOf("") }

        PogodnickTheme {
            Column {
                MyAppBar(stringResource(id = R.string.add_city))
                InputText {
                    text = it
                }
                Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Bottom) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Button(
                            onClick = {
                                mainViewModel.saveCity(
                                    CardWeather(
                                        cityName = text,
                                        favorite = false,
                                        howDegrease = ""
                                    )
                                )
                                navController.popBackStack()
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

        TextField(
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


