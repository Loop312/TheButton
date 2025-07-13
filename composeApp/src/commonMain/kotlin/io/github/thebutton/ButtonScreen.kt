package io.github.thebutton

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.min

@Composable
fun ButtonScreen (navSettings: () -> Unit, navBack: () -> Unit, id: Int, increment: Int) {
    // 1. State for the counter, initialized to 0. This will be updated from the database.
    var count by remember { mutableStateOf(0) }

    // 2. State to track if the database data has been loaded.
    var isLoading by remember { mutableStateOf(true) }

    // 3. LaunchedEffect to load data from the database when the Composable first enters composition.
    // Unit as key means it runs once when the component is first composed.
    LaunchedEffect(Unit) {
        try {
            // Execute the selectLastCount query.
            // SQLDelight automatically generates methods based on your .sq queries.
            // appDatabaseQueries is the generated accessor for your queries.
            val lastCount = database.selectCount(id.toLong()).executeAsOneOrNull()

            // Update the 'count' state with the loaded value.
            // If lastCount is null (no entries in DB yet), default to 0.
            // SQL INTEGER maps to Kotlin Long, so convert to Int.
            count = lastCount?.count?.toInt() ?: 0
        } catch (e: Exception) {
            // Print any errors during database loading. In a real app, you might show a user-friendly message.
            println("Error loading count from database: ${e.message}")
        } finally {
            // Once loading is complete (or an error occurred), set isLoading to false.
            isLoading = false
        }
    }

    // BoxWithConstraints allows you to get the available dimensions for responsive sizing.
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        // Calculate dynamic size based on parent's dimensions
        val minDimension = min(maxWidth.value, maxHeight.value)
        // Button size scales with screen size and the current count.
        val dynamicButtonSize = (minDimension / 5) + (count / 2)

        // Font size scales with the button size for readability.
        val dynamicFontSize = (dynamicButtonSize / 5 + 5).sp

        Row {
            Button({navBack()}) {
                Text("Back")
            }
            Button({navSettings()}) {
                Text("Settings")
            }
        }

        // Conditionally render based on loading state.
        if (isLoading) {
            // Show a loading indicator while data is being fetched from the database.
            Text("Loading count...", fontSize = 24.sp, modifier = Modifier.align(Alignment.Center))

        } else {
            // Main content once data is loaded.
            // The main interactive button.
            Button(
                onClick = {
                    // 4. Increment the count.
                    count += increment

                    // 5. Save the updated count to the database.
                    // We are inserting a new record each time the button is clicked.
                    // The 'selectLastCount' query will always retrieve the most recent one.
                    // Convert Int count to Long for SQLDelight's insert method.
                    try {
                        database.replaceCount(id.toLong(), count.toLong())
                        println("Count saved: $count") // For debugging
                        println(database.selectAll().executeAsList())
                    } catch (e: Exception) {
                        println("Error saving count to database: ${e.message}")
                    }
                },
                shape = CircleShape,
                modifier = Modifier
                    .size(dynamicButtonSize.dp) // Use dynamic size here
                    .align(Alignment.Center)
            ) {
                Text(
                    text = "$count",
                    fontSize = dynamicFontSize,
                )
            }
            // Add a "Reset" button for testing and demonstration purposes.
            // This clears all entries in the database and resets the in-memory count.
            Button(
                onClick = {
                    count = 0
                    try {
                        database.removeAllCounts()
                        println("Database cleared and count reset.")
                    } catch (e: Exception) {
                        println("Error clearing database: ${e.message}")
                    }
                },
                modifier = Modifier.align(Alignment.BottomCenter) // Align the reset button
            ) {
                Text("Reset Count & Clear DB")
            }
        }
    }
}
