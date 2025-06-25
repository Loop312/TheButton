package io.github.thebutton


import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import java.util.Properties


actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return JdbcSqliteDriver("jdbc:sqlite:AppDatabase.db", Properties(), AppDatabase.Schema)
    }
}

actual val database = createDatabase(DriverFactory())