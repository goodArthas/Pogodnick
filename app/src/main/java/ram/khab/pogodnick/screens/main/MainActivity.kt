package ram.khab.pogodnick.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.getViewModel
import ram.khab.pogodnick.screens.CITY_ADD_SCREEN_NAME
import ram.khab.pogodnick.screens.CityAddScreen
import ram.khab.pogodnick.screens.WEATHER_IN_CITY_SCREEN_NAME
import ram.khab.pogodnick.screens.WeatherInCitiesScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val mainVm = getViewModel<MainViewModel>()
            NavHost(navController = navController, startDestination = WEATHER_IN_CITY_SCREEN_NAME) {
                composable(WEATHER_IN_CITY_SCREEN_NAME) {
                    WeatherInCitiesScreen().Screen(
                        navController,
                        mainVm
                    )
                }
                composable(CITY_ADD_SCREEN_NAME) {
                    CityAddScreen().Screen(
                        navController,
                        mainVm
                    )
                }
            }
        }
    }
}

