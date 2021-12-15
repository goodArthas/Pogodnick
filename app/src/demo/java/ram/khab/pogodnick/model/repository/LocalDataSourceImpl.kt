package ram.khab.pogodnick.model.repository

import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.model.room.AppDatabase

class LocalDataSourceImpl(
    private val database: AppDatabase
) : LocalDataSource {

    private val list = mutableListOf<CardWeather>(
        CardWeather(0, "Москва", "-5", false),
        CardWeather(0, "Елабуга", "-6", true),
        CardWeather(0, "Кукмор", "16", false),
        CardWeather(0, "Москва", "-3", true),
        /*CardWeather("Питер", "9", true),
        CardWeather("Кавказ", "-1", false),
        CardWeather("Сочи", "0", true),
        CardWeather("Пермь", "4", false),
        CardWeather("Нижний новгород", "4", false),
        CardWeather("Бетьки", "4", false),
        CardWeather("Игенче", "4", false),*/
    )

    override suspend fun getAllWeather(): List<CardWeather> = list

    override suspend fun deleteWeather(city: CardWeather) {
        list.remove(city)
    }

    override suspend fun saveWeather(cardWeather: CardWeather) {
        list.add(cardWeather)
    }

}