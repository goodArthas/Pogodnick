package ram.khab.pogodnick.domain.usecases

import kotlinx.coroutines.flow.*
import ram.khab.pogodnick.domain.entities.pojo.WeatherDetails
import ram.khab.pogodnick.domain.repository.Repository

class DetailWeatherFetcherUseCase(
    private val repository: Repository
) {

    fun execute(cityName: String): Flow<WeatherDetails> = flow {
        repository
            .getWeatherDetails(cityName)
            .catch {
                emitDetailsFromAnotherSource(cityName)
            }
            .collect { weatherDetails ->
                emit(weatherDetails)
            }
    }

    private suspend fun FlowCollector<WeatherDetails>.emitDetailsFromAnotherSource(cityName: String) {
        repository
            .getAllWeather()
            .collect { listCardWeather ->
                listCardWeather.filter { cardWeather ->
                    cardWeather.cityName == cityName
                }.map { card ->
                    return@map WeatherDetails(temperatureHeader = card.howDegrease)
                }.asFlow().collect { weatherDetail ->
                    emit(weatherDetail)
                }
            }
    }
}
