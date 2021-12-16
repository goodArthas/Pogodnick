package ram.khab.pogodnick.model.repository

import ram.khab.pogodnick.BuildConfig
import ram.khab.pogodnick.model.pojo.weather_from_web.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/forecast")
    suspend fun requestWeatherByCityName(
        @Query("q") cityName: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ru",
        @Query("appid") apikey: String = BuildConfig.API_KEY
    ): Weather
}