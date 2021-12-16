package ram.khab.pogodnick.model.repository

import ram.khab.pogodnick.model.pojo.weather_from_web.Weather

class RemoteDataSourceImpl(
    private val weatherApi: WeatherApi
) : RemoteDataSource {

    override suspend fun getWeatherByCityName(cityName: String): Weather =
        weatherApi.requestWeatherByCityName(cityName)

}