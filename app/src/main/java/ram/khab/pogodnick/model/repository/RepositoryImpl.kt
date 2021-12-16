package ram.khab.pogodnick.model.repository

import ram.khab.pogodnick.model.mapToCard
import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.model.repository.local.LocalDataSource
import ram.khab.pogodnick.model.repository.remote.RemoteDataSource

class RepositoryImpl(
    private val localRepo: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override suspend fun getWeatherByCityName(cityName: String): CardWeather =
        remoteDataSource.getWeatherByCityName(cityName).mapToCard()


    override suspend fun getAllWeather(): List<CardWeather> = localRepo.getAllWeather()

    override suspend fun deleteWeather(city: CardWeather) = localRepo.deleteWeather(city)

    override suspend fun saveCity(cardWeather: CardWeather) = localRepo.saveCity(cardWeather)

}