package ram.khab.pogodnick.data.repository.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ram.khab.pogodnick.data.room.AppDatabase
import ram.khab.pogodnick.domain.entities.pojo.CardWeather

class LocalDataSourceImpl(
    database: AppDatabase
) : LocalDataSource {

    private val dao = database.weatherCardDao()

    override fun getAllWeather(): Flow<List<CardWeather>> = dao.getAll()

    override fun deleteWeather(cardWeather: CardWeather): Flow<Int> =
        flow {
            emit(dao.delete(cardWeather))
        }

    override fun saveCity(cardWeather: CardWeather): Flow<Long> =
        flow { emit(dao.save(cardWeather)) }

    override fun updateWeather(cityCardWeatherList: List<CardWeather>): Flow<Int> =
        flow { emit(dao.update(cityCardWeatherList)) }

}
