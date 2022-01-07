package ram.khab.pogodnick.domain

import kotlinx.coroutines.flow.*
import ram.khab.pogodnick.model.pojo.WeatherDetails
import ram.khab.pogodnick.model.repository.Repository

class DetailWeatherFetcherUseCase(
    private val repository: Repository
) {

    fun execute(cityName: String): Flow<WeatherDetails> = flow {
        repository
            .getWeatherDetails(cityName)
            .catch {
                repository
                    .getAllWeather()
                    .collect { listCardWeather ->
                        listCardWeather.asFlow().filter {
                            it.cityName == cityName
                        }.map { card ->
                            return@map WeatherDetails(temperatureHeader = card.howDegrease)
                        }.collect { weatherDetail ->
                            emit(weatherDetail)
                        }
                    }
            }
            .collect { weatherDetails ->
                emit(weatherDetails)
            }
    }

}