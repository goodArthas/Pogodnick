package ram.khab.pogodnick.domain.usecases

import kotlinx.coroutines.flow.*
import ram.khab.pogodnick.domain.entities.pojo.CardWeather
import ram.khab.pogodnick.domain.repository.Repository

class UpdaterDataInWeatherCardUseCase(
    private val repository: Repository
) {

    fun execute(): Flow<Boolean> = flow {
        repository.getAllWeather().map { cardWeathers ->
            getUpdatedWeatherList(cardWeathers)
        }.collect { cardWeathers ->
            repository.updateWeather(cardWeathers).collect()
            emit(true)
        }
    }

    private suspend fun getUpdatedWeatherList(cardWeathers: List<CardWeather>) =
        cardWeathers.asFlow()
            .flatMapMerge { cardWeather ->
                repository.getWeather(cardWeather)
            }
            .toList()
}
