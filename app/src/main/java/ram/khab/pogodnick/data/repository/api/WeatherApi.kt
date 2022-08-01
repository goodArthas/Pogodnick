package ram.khab.pogodnick.data.repository.api

import ram.khab.pogodnick.BuildConfig
import ram.khab.pogodnick.domain.entities.pojo.weather_from_web.WeatherMain
import retrofit2.http.GET
import retrofit2.http.Query


private const val WEATHER_BY_CITY_NAME_PATH = "data/2.5/forecast"

private const val CITY_NAME_QUERY_PARAM = "q"
private const val UNITS_QUERY_PARAM = "units"
private const val UNITS_QUERY_VALUE = "metric"
private const val LANGUAGE_QUERY_PARAM = "units"
private const val LANGUAGE_QUERY_VALUE = "ru"
private const val APP_ID_QUERY_PARAM = "appid"
private const val APP_ID_QUERY_VALUE = BuildConfig.API_KEY

interface WeatherApi {
    @GET(WEATHER_BY_CITY_NAME_PATH)
    suspend fun requestWeatherByCityName(
        @Query(CITY_NAME_QUERY_PARAM) cityName: String,
        @Query(UNITS_QUERY_PARAM) units: String = UNITS_QUERY_VALUE,
        @Query(LANGUAGE_QUERY_PARAM) lang: String = LANGUAGE_QUERY_VALUE,
        @Query(APP_ID_QUERY_PARAM) apikey: String = APP_ID_QUERY_VALUE
    ): WeatherMain
}
