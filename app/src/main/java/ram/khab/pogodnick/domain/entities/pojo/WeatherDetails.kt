package ram.khab.pogodnick.domain.entities.pojo

data class WeatherDetails(
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