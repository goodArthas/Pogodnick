package ram.khab.pogodnick.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import ram.khab.pogodnick.R

@Composable
fun MyAppBar(topAppBarText: String) {
    val padding = dimensionResource(id = R.dimen.padding_standard)
    TopAppBar(
        title = {
            Text(text = topAppBarText)
        },
        backgroundColor = PurpleBase,
        contentColor = White,
        navigationIcon = {
            Icon(
                modifier = Modifier.padding(padding),
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.back_arrow)
            )
        }
    )
}