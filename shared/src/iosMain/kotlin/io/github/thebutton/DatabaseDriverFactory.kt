package io.github.thebutton

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import io.github.thebutton.cache.AppDatabase
import io.github.thebutton.DriverFactory

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, "AppDatabase.db")
    }
}

actual val database: AppDatabase = createDatabase(DriverFactory())