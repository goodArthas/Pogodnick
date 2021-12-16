package ram.khab.pogodnick.model

import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.model.pojo.weather_from_web.Weather

fun Weather.mapToCard(): CardWeather = CardWeather(
    cityName = this.city.name,
    howDegrease = this.list[0].main.temp.toString(),
    favorite = false
)