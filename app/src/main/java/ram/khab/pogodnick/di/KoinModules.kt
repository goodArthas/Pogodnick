package ram.khab.pogodnick.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ram.khab.pogodnick.MainViewModel

val appModule = module {
    viewModel { MainViewModel() }
}