package ram.khab.pogodnick.model.repository

import ram.khab.pogodnick.model.pojo.CardWeather

interface Repository {
    suspend fun getWeatherByCityName(cityName: String): CardWeather
    suspend fun getAllWeather(): List<CardWeather>
    suspend fun deleteWeatherByCityName(cityName: String)
    suspend fun saveWeather(cityName: CardWeather)
}