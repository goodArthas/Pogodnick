package ram.khab.pogodnick.model.repository.remote

import kotlinx.coroutines.flow.Flow
import ram.khab.pogodnick.model.pojo.WeatherDetails
import ram.khab.pogodnick.model.pojo.weather_from_web.WeatherMain

interface RemoteDataSource {

    fun getWeatherByCityName(cityName: String): Flow<WeatherMain>
    fun getWeatherDetailsByCityName(cityName: String): Flow<WeatherDetails>

}