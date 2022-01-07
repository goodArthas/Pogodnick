package ram.khab.pogodnick.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.model.repository.Repository

class WeatherCardLikeChangerUseCase(
    private val repository: Repository
) {
    fun execute(cardWeather: CardWeather): Flow<Boolean> = flow {
        repository.updateWeather(listOf(cardWeather)).collect { updateRowCount ->
            emit(updateRowCount >= 1)
        }
    }
}