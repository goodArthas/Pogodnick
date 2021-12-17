package ram.khab.pogodnick.model.repository

import kotlinx.coroutines.flow.Flow
import ram.khab.pogodnick.model.pojo.CardWeather

interface Repository {
    fun getWeatherByCityName(cityName: String, foreignKey: Int = 0): Flow<CardWeather>
    fun getAllWeather(): Flow<List<CardWeather>>
    fun deleteWeather(city: CardWeather): Flow<Int>
    fun saveCity(cardWeather: CardWeather): Flow<Long>
    fun updateWeather(cityCardWeatherList: List<CardWeather>): Flow<Int>
}