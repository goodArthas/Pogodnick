package ram.khab.pogodnick.domain.entities.pojo.weather_from_web


import com.google.gson.annotations.SerializedName

data class WeatherX(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String
)