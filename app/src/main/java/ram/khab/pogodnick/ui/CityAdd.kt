package ram.khab.pogodnick.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import ram.khab.pogodnick.R
import ram.khab.pogodnick.ui.CityAdd.CityAddScreen
import ram.khab.pogodnick.ui.theme.*

object CityAdd {

    @Composable
    fun CityAddScreen() {
        var text: String = ""
        PogodnickTheme {
            Column {
                MyAppBar()
                InputText() {
                    text = it
                }
                Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Bottom) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        AddButton()
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
        OutlinedTextField(
            shape = Shapes.large,
            value = stringResource(id = R.string.enter_city_name),
            onValueChange = { changeTest(it) },
            textStyle = TextStyle(color = BlackTextHint),
            modifier = Modifier
                .padding(horizontal = padding)
                .padding(top = mediumPadding)
                .fillMaxWidth()
        )
    }

    @Composable
    private fun AddButton() {
        val padding = dimensionResource(id = R.dimen.padding_standart)
        val mediumPadding = dimensionResource(id = R.dimen.padding_large)
        val buttonTextSize = fontDimensionResource(id = R.dimen.button_text_size)
        val buttonHeightSize = dimensionResource(id = R.dimen.button_height_size)
        Button(
            onClick = {

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
        CityAddScreen()
    }
}

