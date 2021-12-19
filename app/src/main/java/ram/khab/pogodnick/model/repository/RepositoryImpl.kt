package ram.khab.pogodnick.model.repository

import kotlinx.coroutines.flow.*
import ram.khab.pogodnick.model.mapToCard
import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.model.repository.local.LocalDataSource
import ram.khab.pogodnick.model.repository.remote.RemoteDataSource

class RepositoryImpl(
    private val localRepo: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override fun getWeather(cardWeather: CardWeather): Flow<CardWeather> {
        return remoteDataSource
            .getWeatherByCityName(cardWeather.cityName)
            .map { weather ->
                return@map weather.mapToCard(cardWeather.uid, cardWeather.favorite)
            }
    }

    override fun getAllWeather(): Flow<List<CardWeather>> = localRepo.getAllWeather()

    override fun deleteWeather(city: CardWeather): Flow<Int> = localRepo.deleteWeather(city)

    override fun saveCity(cardWeather: CardWeather): Flow<Long> =
        localRepo.saveCity(cardWeather)

    override fun updateWeather(cityCardWeatherList: List<CardWeather>): Flow<Int> =
        localRepo.updateWeather(cityCardWeatherList)

}