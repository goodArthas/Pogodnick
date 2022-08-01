package ram.khab.pogodnick.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ram.khab.pogodnick.data.repository.local.LocalDataSource
import ram.khab.pogodnick.data.repository.remote.RemoteDataSource
import ram.khab.pogodnick.domain.entities.pojo.CardWeather
import ram.khab.pogodnick.domain.entities.pojo.WeatherDetails
import ram.khab.pogodnick.domain.repository.Repository
import ram.khab.pogodnick.domain.util.mapToCard

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

    override fun getWeatherDetails(cityName: String): Flow<WeatherDetails> =
        remoteDataSource.getWeatherDetailsByCityName(cityName)

    override fun getAllWeather(): Flow<List<CardWeather>> = localRepo.getAllWeather()

    override fun deleteWeather(city: CardWeather): Flow<Int> = localRepo.deleteWeather(city)

    override fun saveCity(cardWeather: CardWeather): Flow<Long> =
        localRepo.saveCity(cardWeather)

    override fun updateWeather(cityCardWeatherList: List<CardWeather>): Flow<Int> =
        localRepo.updateWeather(cityCardWeatherList)
}
