package io.github.thebutton

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen (navSettings: () -> Unit, navButton: (Int) -> Unit) {
    Box (Modifier.fillMaxSize()) {
        Column (Modifier.align(Alignment.Center)) {
            for (i in 1..3) {
                Button({navButton(i)}) {
                    Text("Button $i")
                }
            }
            Button({navSettings()}) {
                Text("Settings")
            }
        }
    }
}
