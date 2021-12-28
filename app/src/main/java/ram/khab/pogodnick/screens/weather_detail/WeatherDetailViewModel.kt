package ram.khab.pogodnick.screens.weather_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ram.khab.pogodnick.model.pojo.WeatherDetails
import ram.khab.pogodnick.model.repository.Repository

class WeatherDetailViewModel(
    private val repository: Repository
) : ViewModel() {

    var dataToUi by mutableStateOf(WeatherDetails())
        private set

    fun getDetailWeather(cityName: String) {
        viewModelScope.launch {
            repository
                .getWeatherDetails(cityName)
                .catch {
                    repository
                        .getAllWeather()
                        .collect { listCardWeather ->
                            val card = listCardWeather.asFlow().filter {
                                it.cityName == cityName
                            }.map { card ->
                                return@map WeatherDetails(temperatureHeader = card.howDegrease)
                            }.collect { weatherDetaild ->
                                dataToUi = weatherDetaild
                            }
                        }
                }
                .collect { weatherDetails ->
                    dataToUi = weatherDetails
                }
        }
    }

}