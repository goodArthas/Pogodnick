package ram.khab.pogodnick.presentation.di

import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ram.khab.pogodnick.data.repository.RepositoryImpl
import ram.khab.pogodnick.data.repository.api.WeatherApi
import ram.khab.pogodnick.data.repository.local.LocalDataSource
import ram.khab.pogodnick.data.repository.local.LocalDataSourceImpl
import ram.khab.pogodnick.data.repository.remote.RemoteDataSource
import ram.khab.pogodnick.data.repository.remote.RemoteDataSourceImpl
import ram.khab.pogodnick.data.room.AppDatabase
import ram.khab.pogodnick.domain.repository.Repository
import ram.khab.pogodnick.domain.usecases.*
import ram.khab.pogodnick.presentation.screens.main.MainViewModel
import ram.khab.pogodnick.presentation.screens.weather_detail.WeatherDetailViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainModule = module {
    viewModel {
        MainViewModel(
            repository = get(),
            removerCityUseCase = get(),
            likeChangerUseCase = get(),
            citySaverUseCase = get(),
            updaterWeatherUseCase = get()
        )
    }
    viewModel {
        WeatherDetailViewModel(
            detailWeatherFetcherUseCase = get()
        )
    }

}

const val IMAGE_SAVE_PATH_KEY = "image_save_key"
private const val IMAGE_SAVE_PATH_VALUE = "https://openweathermap.org/img/wn/"

private const val BASE_URL_KEY = "base_url"
private const val BASE_URL_VALUE = "https://api.openweathermap.org/"
private const val DATA_BASE_NAME = "database-weather"

val repositoryModule = module {
    factory(named(BASE_URL_KEY)) { BASE_URL_VALUE }
    single(named(IMAGE_SAVE_PATH_KEY)) { IMAGE_SAVE_PATH_VALUE }

    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            DATA_BASE_NAME
        ).build()
    }
    single<LocalDataSource> {
        LocalDataSourceImpl(
            database = get()
        )
    }
    single<Repository> {
        RepositoryImpl(
            localRepo = get(),
            remoteDataSource = get()
        )
    }
    single<WeatherApi> {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        Retrofit.Builder()
            .baseUrl(get<String>(qualifier = named("base_url")))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(WeatherApi::class.java)
    }
    factory<RemoteDataSource> {
        RemoteDataSourceImpl(
            weatherApi = get()
        )
    }
}

val useCasesModule = module {
    factory {
        RemoverCityUseCase(
            repository = get()
        )
    }
    factory {
        WeatherCardLikeChangerUseCase(
            repository = get()
        )
    }
    factory {
        CitySaverUseCase(
            repository = get()
        )
    }
    factory {
        UpdaterDataInWeatherCardUseCase(
            repository = get()
        )
    }
    factory {
        DetailWeatherFetcherUseCase(
            repository = get()
        )
    }
}
