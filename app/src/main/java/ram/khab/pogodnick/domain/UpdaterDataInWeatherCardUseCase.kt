package ram.khab.pogodnick.domain

import kotlinx.coroutines.flow.*
import ram.khab.pogodnick.model.repository.Repository

class UpdaterDataInWeatherCardUseCase(
    private val repository: Repository
) {

    fun execute(): Flow<Boolean> = flow {
        repository.getAllWeather().map {
            return@map it.asFlow()
                .flatMapMerge { cardWeather ->
                    repository.getWeather(cardWeather)
                }
                .toList()
        }.collect { listCardWeather ->
            repository.updateWeather(listCardWeather).collect()
            emit(true)
        }
    }

}