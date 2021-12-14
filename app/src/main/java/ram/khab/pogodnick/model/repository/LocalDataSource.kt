package ram.khab.pogodnick.model.repository

import ram.khab.pogodnick.model.pojo.CardWeather

interface LocalDataSource {
    suspend fun getAllWeather(): List<CardWeather>
    suspend fun deleteWeatherByCityName(cityName: String)
    suspend fun saveWeather(cardWeather: CardWeather)
}