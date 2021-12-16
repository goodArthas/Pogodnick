package ram.khab.pogodnick.model.repository.local

import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.model.room.AppDatabase

class LocalDataSourceImpl(
    private val database: AppDatabase
) : LocalDataSource {

    private val dao = database.weatherCardDao()

    override suspend fun getAllWeather(): List<CardWeather> = dao.getAll()

    override suspend fun deleteWeather(cardWeather: CardWeather) = dao.delete(cardWeather)

    override suspend fun saveCity(cardWeather: CardWeather) = dao.save(cardWeather)

}