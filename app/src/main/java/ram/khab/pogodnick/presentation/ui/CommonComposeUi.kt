package ram.khab.pogodnick.ui.theme

import androidx.compose.foundation.clickable
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
import ram.khab.pogodnick.presentation.ui.theme.PurpleBase
import ram.khab.pogodnick.presentation.ui.theme.White

@Composable
fun MyAppBar(topAppBarText: String, backArrowPress: () -> Unit) {
    val padding = dimensionResource(id = R.dimen.padding_standard)
    TopAppBar(
        title = {
            Text(text = topAppBarText)
        },
        backgroundColor = PurpleBase,
        contentColor = White,
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .padding(padding)
                    .clickable { backArrowPress() },
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.back_arrow)
            )
        }
    )
}
