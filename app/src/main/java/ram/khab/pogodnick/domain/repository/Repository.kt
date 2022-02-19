package ram.khab.pogodnick.domain.repository

import kotlinx.coroutines.flow.Flow
import ram.khab.pogodnick.domain.entities.pojo.CardWeather
import ram.khab.pogodnick.domain.entities.pojo.WeatherDetails

interface Repository {
    fun getWeather(cardWeather: CardWeather): Flow<CardWeather>
    fun getWeatherDetails(cityName: String): Flow<WeatherDetails>
    fun getAllWeather(): Flow<List<CardWeather>>
    fun deleteWeather(city: CardWeather): Flow<Int>
    fun saveCity(cardWeather: CardWeather): Flow<Long>
    fun updateWeather(cityCardWeatherList: List<CardWeather>): Flow<Int>
}