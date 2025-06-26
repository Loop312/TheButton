package io.github.thebutton

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        //alwaysOnTop = true,
        title = "The Button",
    ) {
        App()
    }
}

actual val database = createDatabase(DriverFactory())