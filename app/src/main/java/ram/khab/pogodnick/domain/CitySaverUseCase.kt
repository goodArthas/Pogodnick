package ram.khab.pogodnick.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.model.repository.Repository

class CitySaverUseCase(
    private val repository: Repository
) {

    fun execute(cityName: String): Flow<Boolean> = flow {
        repository.getWeather(
            CardWeather(cityName = cityName, favorite = false, howDegrease = "")
        )
            .collect { cardWeather ->
                repository.saveCity(cardWeather).collect()
            }
    }

}