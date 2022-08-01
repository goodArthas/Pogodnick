package ram.khab.pogodnick.domain.entities.pojo.weather_from_web


import com.google.gson.annotations.SerializedName

data class WeatherMain(
    @SerializedName("cod")
    val cod: String,
    @SerializedName("message")
    val message: Int,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("list")
    val list: List<Weathers>,
    @SerializedName("city")
    val city: City
)
