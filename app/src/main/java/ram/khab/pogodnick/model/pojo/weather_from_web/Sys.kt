package ram.khab.pogodnick.model.pojo.weather_from_web


import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("pod")
    val pod: String
)