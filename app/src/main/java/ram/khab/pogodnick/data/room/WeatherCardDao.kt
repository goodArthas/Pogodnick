package ram.khab.pogodnick.data.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ram.khab.pogodnick.domain.entities.pojo.CardWeather

@Dao
interface WeatherCardDao {
    @Query("SELECT * FROM cardweather")
    fun getAll(): Flow<List<CardWeather>>

    @Delete
    suspend fun delete(cityCardWeather: CardWeather): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(cityCardWeather: CardWeather): Long

    @Update
    suspend fun update(cityCardWeatherList: List<CardWeather>): Int
}
