package ram.khab.pogodnick.model.pojo.repository

import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.model.repository.Repository

class RepositoryImpl : Repository {

    override suspend fun getWeather(cityName: String): List<CardWeather> = listOf<CardWeather>(
        CardWeather("Москва", "-5", false),
        CardWeather("Елабуга", "-6", true),
        /* CardWeather("Кукмор", "16", false),
         CardWeather("Москва", "-3", true),
         CardWeather("Питер", "9", true),
         CardWeather("Кавказ", "-1", false),
         CardWeather("Сочи", "0", true),
         CardWeather("Пермь", "4", false),
         CardWeather("Нижний новгород", "4", false),
         CardWeather("Бетьки", "4", false),
         CardWeather("Игенче", "4", false),*/
    )

}