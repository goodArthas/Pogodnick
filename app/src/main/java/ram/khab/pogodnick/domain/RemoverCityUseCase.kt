package ram.khab.pogodnick.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.model.repository.Repository

class RemoverCityUseCase(
    private val repository: Repository
) {
    fun execute(city: CardWeather): Flow<Boolean> = flow {
        repository.deleteWeather(city)
            .collect { removeRowCount ->
                emit(removeRowCount >= 1)
            }
    }
}