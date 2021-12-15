package ram.khab.pogodnick.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import ram.khab.pogodnick.R
import ram.khab.pogodnick.ui.main.MainViewModel
import ram.khab.pogodnick.ui.theme.*

object CityAdd {

    @Composable
    fun CityAddScreen(navController: NavController, mainViewModel: MainViewModel) {
        PogodnickTheme {
            var text by rememberSaveable { mutableStateOf("") }

            PogodnickTheme {
                Column {
                    MyAppBar()
                    InputText() {
                        text = it
                    }
                    Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Bottom) {
                        Row(verticalAlignment = Alignment.Bottom) {
                            AddButton() {
                                mainViewModel.addCity(text)
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun MyAppBar() {
        val padding = dimensionResource(id = R.dimen.padding_standart)
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.add_city))
            },
            backgroundColor = PurpleBase,
            contentColor = White,
            navigationIcon = {
                Icon(
                    modifier = Modifier.padding(padding),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.add_city)
                )
            }
        )
    }

    @Composable
    private fun InputText(changeTest: (String) -> Unit) {
        val padding = dimensionResource(id = R.dimen.padding_standart)
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

    @Composable
    private fun AddButton(click: () -> Unit) {
        val padding = dimensionResource(id = R.dimen.padding_standart)
        val mediumPadding = dimensionResource(id = R.dimen.padding_large)
        val buttonTextSize = fontDimensionResource(id = R.dimen.button_text_size)
        val buttonHeightSize = dimensionResource(id = R.dimen.button_height_size)
        Button(
            onClick = {
                click()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = padding, end = padding, bottom = mediumPadding)
                .height(buttonHeightSize)
        )
        {
            Text(text = stringResource(id = R.string.add), fontSize = buttonTextSize)
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CityAddScreenPreview() {
    Column {

    }
}

