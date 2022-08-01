package ram.khab.pogodnick.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import ram.khab.pogodnick.domain.entities.pojo.CardWeather
import ram.khab.pogodnick.domain.repository.Repository

class CitySaverUseCase(
    private val repository: Repository
) {

    fun execute(cityName: String): Flow<Boolean> = flow {
        repository.getWeather(getCardWeatherDefault(cityName))
            .collect { cardWeather ->
                repository.saveCity(cardWeather).collect()
            }
    }

    private fun getCardWeatherDefault(cityName: String) =
        CardWeather(cityName = cityName, favorite = false, howDegrease = "")
}
