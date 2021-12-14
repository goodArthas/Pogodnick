package ram.khab.pogodnick

import android.app.Application
import android.util.Log
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ram.khab.pogodnick.di.mainModule
import ram.khab.pogodnick.di.repositoryModule

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("QWE", "onCreate: ")
        startKoin {
            androidContext(applicationContext)
            modules(mainModule, repositoryModule)
        }
    }
}