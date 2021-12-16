package ram.khab.pogodnick.model.repository.local

import kotlinx.coroutines.flow.Flow
import ram.khab.pogodnick.model.pojo.CardWeather

interface LocalDataSource {
   fun getAllWeather(): Flow<List<CardWeather>>
   fun deleteWeather(cardWeather: CardWeather): Flow<Int>
   fun saveCity(cardWeather: CardWeather): Flow<Long>
}