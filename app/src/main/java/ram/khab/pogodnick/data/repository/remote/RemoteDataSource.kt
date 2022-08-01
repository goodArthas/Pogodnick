package ram.khab.pogodnick.data.repository.remote

import kotlinx.coroutines.flow.Flow
import ram.khab.pogodnick.domain.entities.pojo.WeatherDetails
import ram.khab.pogodnick.domain.entities.pojo.weather_from_web.WeatherMain

interface RemoteDataSource {
    fun getWeatherByCityName(cityName: String): Flow<WeatherMain>
    fun getWeatherDetailsByCityName(cityName: String): Flow<WeatherDetails>
}
