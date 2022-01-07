package ram.khab.pogodnick.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ram.khab.pogodnick.domain.entities.pojo.CardWeather

@Database(entities = [CardWeather::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherCardDao(): WeatherCardDao
}