package ram.khab.pogodnick.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.getViewModel
import ram.khab.pogodnick.model.Screens
import ram.khab.pogodnick.screens.CityAdd.CityAddScreen
import ram.khab.pogodnick.screens.WeatherInCities.WeatherInTheCities

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val mainVm = getViewModel<MainViewModel>()
            NavHost(navController = navController, startDestination = "weatherInTheCities") {
                composable(Screens.WeatherInTheCities) { WeatherInTheCities(navController, mainVm) }
                composable(Screens.CityAddScreen) { CityAddScreen(navController, mainVm) }
            }
        }
    }
}

