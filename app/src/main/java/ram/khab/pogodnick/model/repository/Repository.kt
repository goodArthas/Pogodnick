package ram.khab.pogodnick.model.repository

import ram.khab.pogodnick.model.pojo.CardWeather

interface Repository {
    suspend fun getWeather(cityName: String): List<CardWeather>
}