package io.github.thebutton

import android.app.Application
import android.content.Context

// This class runs once when your application process starts.
class MyApplication : Application() {

    // We use a companion object to make the appContext accessible statically
    // throughout your application.
    companion object {
        lateinit var appContext: Context
            private set // 'private set' means it can only be set within this class.
    }

    // onCreate is called by the Android system when the application is first created.
    override fun onCreate() {
        super.onCreate()
        // 'applicationContext' here refers to the global context for your application.
        // We store it in our static 'appContext' variable.
        appContext = applicationContext
    }
}

actual val database = createDatabase(DriverFactory(MyApplication.appContext)).appDatabaseQueries