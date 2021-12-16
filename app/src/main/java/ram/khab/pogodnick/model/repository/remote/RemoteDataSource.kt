package ram.khab.pogodnick.model.repository.remote

import kotlinx.coroutines.flow.Flow
import ram.khab.pogodnick.model.pojo.weather_from_web.Weather

interface RemoteDataSource {

    fun getWeatherByCityName(cityName: String): Flow<Weather>

}