package ram.khab.pogodnick.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import ram.khab.pogodnick.data.repository.Repository
import ram.khab.pogodnick.domain.entities.pojo.CardWeather

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