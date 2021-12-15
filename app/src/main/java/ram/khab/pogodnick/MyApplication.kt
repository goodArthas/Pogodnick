package ram.khab.pogodnick

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ram.khab.pogodnick.di.mainModule
import ram.khab.pogodnick.di.repositoryModule

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(mainModule, repositoryModule)
        }
    }
}