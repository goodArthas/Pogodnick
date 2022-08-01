package ram.khab.pogodnick.data.repository.local

import kotlinx.coroutines.flow.Flow
import ram.khab.pogodnick.domain.entities.pojo.CardWeather

interface LocalDataSource {
   fun getAllWeather(): Flow<List<CardWeather>>
   fun deleteWeather(cardWeather: CardWeather): Flow<Int>
   fun saveCity(cardWeather: CardWeather): Flow<Long>
   fun updateWeather(cityCardWeatherList: List<CardWeather>): Flow<Int>
}
