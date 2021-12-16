package ram.khab.pogodnick.model.repository.remote

import ram.khab.pogodnick.model.pojo.weather_from_web.Weather
import ram.khab.pogodnick.model.repository.api.WeatherApi

class RemoteDataSourceImpl(
    private val weatherApi: WeatherApi
) : RemoteDataSource {

    override suspend fun getWeatherByCityName(cityName: String): Weather =
        weatherApi.requestWeatherByCityName(cityName)

}