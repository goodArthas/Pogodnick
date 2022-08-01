package ram.khab.pogodnick.domain.entities.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CardWeather(
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0,
    val cityName: String,
    val howDegrease: String,
    val favorite: Boolean
)
