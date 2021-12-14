package ram.khab.pogodnick.model.pojo.weather_from_web


import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    val all: Int
)