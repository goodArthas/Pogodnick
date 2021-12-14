package ram.khab.pogodnick.model.pojo.weather_from_web


import com.google.gson.annotations.SerializedName

data class Weather(
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