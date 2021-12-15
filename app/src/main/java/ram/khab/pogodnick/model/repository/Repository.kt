package ram.khab.pogodnick.model.repository

import ram.khab.pogodnick.model.pojo.CardWeather

interface Repository {
    suspend fun getWeatherByCityName(cityName: String): CardWeather
    suspend fun getAllWeather(): List<CardWeather>
    suspend fun deleteWeather(city: CardWeather)
    suspend fun saveWeather(cityName: CardWeather)
}