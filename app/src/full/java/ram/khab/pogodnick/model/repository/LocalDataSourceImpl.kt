package ram.khab.pogodnick.model.repository

import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.model.room.AppDatabase

class LocalDataSourceImpl(
    private val database: AppDatabase
) : LocalDataSource {

    private val dao = database.weatherCardDao()

    override suspend fun getAllWeather(): List<CardWeather> = dao.getAll()

    override suspend fun deleteWeatherByCityName(cityName: String) = dao.delete(cityName)

    override suspend fun saveWeather(cardWeather: CardWeather) = dao.saveWeather(cardWeather)

}