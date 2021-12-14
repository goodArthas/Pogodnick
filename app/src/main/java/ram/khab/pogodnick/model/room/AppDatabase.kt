package ram.khab.pogodnick.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ram.khab.pogodnick.model.pojo.CardWeather

@Database(entities = [CardWeather::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherCardDao(): WeatherCardDao
}