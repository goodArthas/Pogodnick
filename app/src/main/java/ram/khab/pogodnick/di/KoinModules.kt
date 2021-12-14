package ram.khab.pogodnick.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ram.khab.pogodnick.model.pojo.repository.RepositoryImpl
import ram.khab.pogodnick.model.repository.Repository
import ram.khab.pogodnick.ui.main.MainViewModel

val appModule = module {
    single<Repository> { RepositoryImpl() }
    viewModel { MainViewModel(get()) }
}