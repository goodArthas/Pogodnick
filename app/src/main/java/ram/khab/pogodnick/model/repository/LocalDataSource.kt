package ram.khab.pogodnick.model.repository

import ram.khab.pogodnick.model.pojo.CardWeather

interface LocalDataSource {
    suspend fun getAllWeather(): List<CardWeather>
    suspend fun deleteWeather(cardWeather: CardWeather)
    suspend fun saveCity(cardWeather: CardWeather)
}