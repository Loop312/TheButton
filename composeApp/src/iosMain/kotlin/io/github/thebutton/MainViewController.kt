package io.github.thebutton

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController { App() }

actual val database = createDatabase(DriverFactory()).appDatabaseQueries