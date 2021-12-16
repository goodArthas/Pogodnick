package ram.khab.pogodnick.model.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ram.khab.pogodnick.model.pojo.CardWeather

@Dao
interface WeatherCardDao {
    @Query("SELECT * FROM cardweather")
    suspend fun getAll(): List<CardWeather>

    @Delete()
    suspend fun delete(cityCardWeather: CardWeather)

    @Insert
    suspend fun save(cityCardWeather: CardWeather)

}