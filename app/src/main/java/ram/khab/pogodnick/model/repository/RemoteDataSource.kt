package ram.khab.pogodnick.model.repository

import ram.khab.pogodnick.model.pojo.weather_from_web.Weather

interface RemoteDataSource {

    suspend fun getWeatherByCityName(cityName: String): Weather

}