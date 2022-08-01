package ram.khab.pogodnick.domain.entities.pojo.weather_from_web


import com.google.gson.annotations.SerializedName

data class Weathers(
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("dt_txt")
    val dtTxt: String,
    @SerializedName("main")
    val main: Main,
    @SerializedName("pop")
    val pop: Double,
    @SerializedName("snow")
    val snow: Snow,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    val weather: List<WeatherX>,
    @SerializedName("wind")
    val wind: Wind
)
