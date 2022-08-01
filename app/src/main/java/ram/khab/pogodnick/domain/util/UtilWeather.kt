package ram.khab.pogodnick.domain.util

import ram.khab.pogodnick.domain.entities.pojo.CardWeather
import ram.khab.pogodnick.domain.entities.pojo.WeatherByDays
import ram.khab.pogodnick.domain.entities.pojo.WeatherDetails
import ram.khab.pogodnick.domain.entities.pojo.weather_from_web.WeatherMain
import java.text.SimpleDateFormat

fun WeatherMain.mapToCard(foreignKey: Int, favorite: Boolean): CardWeather = CardWeather(
    uid = foreignKey,
    cityName = this.city.name,
    howDegrease = this.list[0].main.temp.weatherFormat(),
    favorite = favorite
)

fun WeatherMain.mapToWeatherDetails(): WeatherDetails {
    val temp = this.list[0].main.temp.weatherFormat()
    val icon = this.list[0].weather[0].icon
    val iconDescription = this.list[0].weather[0].description
    val feelsLike = this.list[0].main.feelsLike.weatherFormat()
    val weather = iconDescription
    val visibility = this.list[0].visibility
    val pressure = this.list[0].main.pressure.toString()
    val humidity = this.list[0].main.humidity.toString()
    val windSpeed = this.list[0].wind.speed.toString()
    val windDirection = this.list[0].wind.deg.windDirection()
    val list = listOf(
        WeatherByDays(
            this.list[0].dtTxt.timeFormat(),
            this.list[0].weather[0].icon,
            this.list[0].weather[0].description,
            this.list[0].main.temp.weatherFormat()
        ),
        WeatherByDays(
            this.list[12].dtTxt.timeFormat(),
            this.list[12].weather[0].icon,
            this.list[12].weather[0].description,
            this.list[12].main.temp.weatherFormat()
        ),
        WeatherByDays(
            this.list[20].dtTxt.timeFormat(),
            this.list[20].weather[0].icon,
            this.list[20].weather[0].description,
            this.list[20].main.temp.weatherFormat()
        )
    )

    return WeatherDetails(
        temperatureHeader = temp,
        weatherHeaderIconUrl = icon,
        weatherHeaderIconDescription = iconDescription,
        feelsLikeTemperature = feelsLike,
        weather = weather,
        visibilityRange = visibility.toString(),
        pressure = pressure,
        humidity = humidity,
        windSpeed = windSpeed,
        windDirection = windDirection,
        weathersList = list
    )
}


private fun Double.weatherFormat(): String = Math.round(this).toString()

private const val YEAR_MONTH_DAY_FULL_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
private const val DAY_MONTH_TIME_FORMAT = "dd.MM"

private fun String.timeFormat(): String {
    val parser = SimpleDateFormat(YEAR_MONTH_DAY_FULL_TIME_FORMAT)
    val formatter = SimpleDateFormat(DAY_MONTH_TIME_FORMAT)
    val output = formatter.format(parser.parse(this))
    return output
}

private fun Int.windDirection(): String {
    val directionsArray = arrayOf("C", "CВ", "В", "ЮВ", "Ю", "ЮЗ", "З", "СЗ")
    val directionFormula = (this * 8 / 360)
    return directionsArray[directionFormula]
}
