package io.github.thebutton

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SettingsScreen (navBack: () -> Unit, navController: NavController) {
    val savedStateHandler = navController.previousBackStackEntry?.savedStateHandle
    var increment by remember { mutableStateOf(savedStateHandler?.get<Int>("increment") ?: 0) }
    Box (Modifier.fillMaxSize()) {
        Column (Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Settings")
            Text("Increment: $increment")
            Slider(
                value = increment.toFloat(),
                onValueChange = {
                    increment = it.toInt()
                    savedStateHandler?.set("increment", increment)
                },
                valueRange = 0f..10f,
                modifier = Modifier.size(100.dp, 20.dp)
            )
            Button({navBack()}) {
                Text("Back")
            }
        }
    }
}