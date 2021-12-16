package ram.khab.pogodnick.di

import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ram.khab.pogodnick.model.repository.*
import ram.khab.pogodnick.model.room.AppDatabase
import ram.khab.pogodnick.ui.main.MainViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainModule = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    factory(named("base_url")) { "https://api.openweathermap.org/" }

    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "database-weather"
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
        val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
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