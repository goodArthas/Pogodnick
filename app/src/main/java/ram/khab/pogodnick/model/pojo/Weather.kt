package ram.khab.pogodnick.model.pojo


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