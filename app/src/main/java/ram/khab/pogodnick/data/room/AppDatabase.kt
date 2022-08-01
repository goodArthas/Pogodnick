package ram.khab.pogodnick.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ram.khab.pogodnick.domain.entities.pojo.CardWeather

private const val DB_VERSION = 1

@Database(entities = [CardWeather::class], version = DB_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherCardDao(): WeatherCardDao
}
