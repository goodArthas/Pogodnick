package ram.khab.pogodnick.model.repository

import kotlinx.coroutines.flow.Flow
import ram.khab.pogodnick.model.pojo.CardWeather

interface Repository {
    fun getWeather(cardWeather: CardWeather): Flow<CardWeather>
    fun getAllWeather(): Flow<List<CardWeather>>
    fun deleteWeather(city: CardWeather): Flow<Int>
    fun saveCity(cardWeather: CardWeather): Flow<Long>
    fun updateWeather(cityCardWeatherList: List<CardWeather>): Flow<Int>
}