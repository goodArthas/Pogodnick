package ram.khab.pogodnick

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.koin.androidx.compose.getViewModel
import ram.khab.pogodnick.screens.CITY_ADD_SCREEN_NAME
import ram.khab.pogodnick.screens.CityAddScreen
import ram.khab.pogodnick.screens.WEATHER_IN_CITY_SCREEN_NAME
import ram.khab.pogodnick.screens.WeatherInCitiesScreen
import ram.khab.pogodnick.screens.main.MainViewModel
import ram.khab.pogodnick.screens.weather_detail.WEATHER_DETAIL_SCREEN_NAME
import ram.khab.pogodnick.screens.weather_detail.WeatherDetailScreen

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