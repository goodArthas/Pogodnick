package ram.khab.pogodnick.presentation.screens.weather_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ram.khab.pogodnick.domain.entities.pojo.WeatherDetails
import ram.khab.pogodnick.domain.usecases.DetailWeatherFetcherUseCase

class WeatherDetailViewModel(
    private val detailWeatherFetcherUseCase: DetailWeatherFetcherUseCase
) : ViewModel() {

    var dataToUi by mutableStateOf(WeatherDetails())
        private set

    fun getDetailWeather(cityName: String) {
        viewModelScope.launch {
            detailWeatherFetcherUseCase
                .execute(cityName)
                .catch {
                    TODO()
                }
                .collect { weatherDetails ->
                    dataToUi = weatherDetails
                }
        }
    }

}