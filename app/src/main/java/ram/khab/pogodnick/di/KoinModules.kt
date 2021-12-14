package ram.khab.pogodnick.di

import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ram.khab.pogodnick.model.pojo.repository.RepositoryImpl
import ram.khab.pogodnick.model.repository.LocalDataSource
import ram.khab.pogodnick.model.repository.LocalDataSourceImpl
import ram.khab.pogodnick.model.repository.Repository
import ram.khab.pogodnick.model.room.AppDatabase
import ram.khab.pogodnick.ui.main.MainViewModel

val mainModule = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
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
            localRepo = get()
        )
    }
}