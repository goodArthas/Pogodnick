package ram.khab.pogodnick.model.pojo.repository

import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.model.repository.LocalDataSource
import ram.khab.pogodnick.model.repository.RemoteDataSource
import ram.khab.pogodnick.model.repository.Repository

class RepositoryImpl(
    private val localRepo: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override suspend fun getWeatherByCityName(cityName: String): CardWeather {
        val weather = remoteDataSource.requestWeatherByCityName(cityName)
        return CardWeather(
            0,
            weather.city.name,
            weather.list[0].main.temp.toString(),
            false
        )

    }

    override suspend fun getAllWeather(): List<CardWeather> = localRepo.getAllWeather()

    override suspend fun deleteWeather(city: CardWeather) {
        localRepo.deleteWeather(city)
    }

    override suspend fun saveWeather(city: CardWeather) {
        localRepo.saveWeather(city)
    }

}