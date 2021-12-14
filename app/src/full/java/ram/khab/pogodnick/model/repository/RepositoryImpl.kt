package ram.khab.pogodnick.model.pojo.repository

import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.model.repository.LocalDataSource
import ram.khab.pogodnick.model.repository.Repository

class RepositoryImpl(
    private val localRepo: LocalDataSource
) : Repository {

    override suspend fun getWeatherByCityName(cityName: String): CardWeather {
        TODO("Not yet implemented")
    }

    override suspend fun getAllWeather(): List<CardWeather> = localRepo.getAllWeather()

    override suspend fun deleteWeatherByCityName(cityName: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveWeather(cityName: CardWeather) {
        TODO("Not yet implemented")
    }

}