package ram.khab.pogodnick.data.repository.api

import ram.khab.pogodnick.BuildConfig
import ram.khab.pogodnick.domain.entities.pojo.weather_from_web.WeatherMain
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/forecast")
    suspend fun requestWeatherByCityName(
        @Query("q") cityName: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ru",
        @Query("appid") apikey: String = BuildConfig.API_KEY
    ): WeatherMain
}