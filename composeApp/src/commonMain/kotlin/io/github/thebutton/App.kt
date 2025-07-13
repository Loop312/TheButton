package io.github.thebutton

import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview()
fun App() {
    Nav().activate()
}

expect val database: AppDatabaseQueries