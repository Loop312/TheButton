package io.github.thebutton

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController { App() }

actual fun createKeyValueStore(): KeyValueStore {
    TODO("Not yet implemented")
}