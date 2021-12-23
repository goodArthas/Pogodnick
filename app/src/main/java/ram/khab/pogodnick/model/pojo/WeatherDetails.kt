package ram.khab.pogodnick.model.pojo

data class WeatherDetails(
    val cityName: String = "",
    val temperatureHeader: String = "",
    val weatherHeaderIconUrl: String = "",
    val weatherHeaderIconDescription: String = "",
    val feelsLikeTemperature: String = "",
    val weather: String = "", // пасмурно например
    val visibilityRange: String = "",// в метрах
    val pressure: String = "",
    val humidity: String = "",
    val windSpeed: String = "",
    val windDirection: String = "",
    val weathersList: List<WeatherByDays> = listOf()
)