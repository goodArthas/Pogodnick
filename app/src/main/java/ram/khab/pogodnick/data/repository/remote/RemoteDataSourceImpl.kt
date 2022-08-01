package ram.khab.pogodnick.data.repository.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ram.khab.pogodnick.data.repository.api.WeatherApi
import ram.khab.pogodnick.domain.entities.pojo.WeatherDetails
import ram.khab.pogodnick.domain.entities.pojo.weather_from_web.WeatherMain
import ram.khab.pogodnick.domain.util.mapToWeatherDetails

class RemoteDataSourceImpl(
    private val weatherApi: WeatherApi
) : RemoteDataSource {

    override fun getWeatherByCityName(cityName: String): Flow<WeatherMain> = flow {
        emit(getCityByCityName(cityName))
    }.flowOn(Dispatchers.IO)

    private suspend fun getCityByCityName(cityName: String) =
        weatherApi.requestWeatherByCityName(cityName)

    override fun getWeatherDetailsByCityName(cityName: String): Flow<WeatherDetails> = flow {
        emit(getCityDetailByCityName(cityName))
    }.flowOn(Dispatchers.IO)

    private suspend fun getCityDetailByCityName(cityName: String) =
        weatherApi.requestWeatherByCityName(cityName).mapToWeatherDetails()
}
