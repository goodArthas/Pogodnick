package ram.khab.pogodnick.domain.entities.pojo.weather_from_web


import com.google.gson.annotations.SerializedName

data class Snow(
    @SerializedName("3h")
    val h: Double
)
