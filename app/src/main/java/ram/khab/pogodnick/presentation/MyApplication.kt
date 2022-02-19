package ram.khab.pogodnick.presentation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ram.khab.pogodnick.presentation.di.mainModule
import ram.khab.pogodnick.presentation.di.repositoryModule
import ram.khab.pogodnick.presentation.di.useCasesModule

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(mainModule, repositoryModule, useCasesModule)
        }
    }
}