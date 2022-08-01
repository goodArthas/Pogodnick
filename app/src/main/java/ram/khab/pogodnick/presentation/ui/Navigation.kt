package ram.khab.pogodnick.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.koin.androidx.compose.getViewModel
import ram.khab.pogodnick.presentation.screens.CITY_ADD_SCREEN_NAME
import ram.khab.pogodnick.presentation.screens.CityAddScreen
import ram.khab.pogodnick.presentation.screens.WEATHER_IN_CITY_SCREEN_NAME
import ram.khab.pogodnick.presentation.screens.WeatherInCitiesScreen
import ram.khab.pogodnick.presentation.screens.main.MainViewModel
import ram.khab.pogodnick.presentation.screens.weather_detail.WEATHER_DETAIL_SCREEN_NAME
import ram.khab.pogodnick.presentation.screens.weather_detail.WeatherDetailScreen

class Navigation {
    @Composable
    fun Navigator() {
        val navController = rememberNavController()
        val mainVm = getViewModel<MainViewModel>()
        NavHost(navController = navController, startDestination = WEATHER_IN_CITY_SCREEN_NAME) {
            composable(WEATHER_IN_CITY_SCREEN_NAME) {
                WeatherInCitiesScreen().Screen(
                    mainVm
                ) { path ->
                    navController.navigate(path)
                }
            }
            composable(CITY_ADD_SCREEN_NAME) {
                CityAddScreen().Screen(
                    mainVm
                ) {
                    navController.popBackStack()
                }
            }
            composable(
                "$WEATHER_DETAIL_SCREEN_NAME/{cityName}",
                arguments = listOf(navArgument("cityName") { type = NavType.StringType })
            ) { backStackEntry ->
                WeatherDetailScreen().Screen(
                    backStackEntry.arguments?.getString("cityName")
                ) {
                    navController.popBackStack()
                }
            }
        }
    }
}
