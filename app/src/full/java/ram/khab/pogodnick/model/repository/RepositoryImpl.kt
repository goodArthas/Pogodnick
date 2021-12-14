package ram.khab.pogodnick.model.pojo.repository

import ram.khab.pogodnick.model.pojo.CardWeather
import ram.khab.pogodnick.model.repository.LocalDataSource
import ram.khab.pogodnick.model.repository.RemoteDataSource
import ram.khab.pogodnick.model.repository.Repository

class RepositoryImpl(
    /* val remoteDataSource: RemoteDataSource,
     val localDataSource: LocalDataSource*/
) : Repository {

    override suspend fun getWeather(cityName: String): List<CardWeather> {
        return listOf<CardWeather>(
            CardWeather("Челны", "-5", false)
        )
    }

}