package ram.khab.pogodnick.model.repository.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ram.khab.pogodnick.model.mapToWeatherDetails
import ram.khab.pogodnick.model.pojo.WeatherDetails
import ram.khab.pogodnick.model.pojo.weather_from_web.WeatherMain
import ram.khab.pogodnick.model.repository.api.WeatherApi

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