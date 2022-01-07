package ram.khab.pogodnick.data.repository.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ram.khab.pogodnick.data.repository.api.WeatherApi
import ram.khab.pogodnick.domain.entities.mapToWeatherDetails
import ram.khab.pogodnick.domain.entities.pojo.WeatherDetails
import ram.khab.pogodnick.domain.entities.pojo.weather_from_web.WeatherMain

class RemoteDataSourceImpl(
    private val weatherApi: WeatherApi
) : RemoteDataSource {

    override fun getWeatherByCityName(cityName: String): Flow<WeatherMain> = flow {
        emit(weatherApi.requestWeatherByCityName(cityName))
    }.flowOn(Dispatchers.IO)

    override fun getWeatherDetailsByCityName(cityName: String): Flow<WeatherDetails> = flow {
        emit(weatherApi.requestWeatherByCityName(cityName).mapToWeatherDetails())
    }.flowOn(Dispatchers.IO)

}