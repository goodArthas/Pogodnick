package ram.khab.pogodnick.model.repository.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.model.room.AppDatabase

class LocalDataSourceImpl(
    private val database: AppDatabase
) : LocalDataSource {

    private val dao = database.weatherCardDao()

    override fun getAllWeather(): Flow<List<CardWeather>> = dao.getAll()

    override fun deleteWeather(cardWeather: CardWeather): Flow<Int> =
        flow {
            emit(dao.delete(cardWeather))
        }

    override fun saveCity(cardWeather: CardWeather): Flow<Long> =
        flow { emit(dao.save(cardWeather)) }

}