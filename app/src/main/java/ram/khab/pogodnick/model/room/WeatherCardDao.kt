package ram.khab.pogodnick.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ram.khab.pogodnick.model.pojo.CardWeather

@Dao
interface WeatherCardDao {
    @Query("SELECT * FROM cardweather")
    suspend fun getAll(): List<CardWeather>

    @Query("DELETE FROM cardweather WHERE cityName = :cityName")
    suspend fun delete(cityName: String)

    @Insert
    suspend fun saveWeather(cityCardWeather: CardWeather)

}