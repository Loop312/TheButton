package io.github.thebutton

import androidx.compose.runtime.Composable
import androidx.navigation.NavType.Companion.IntType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class Nav {
    @Composable
    fun activate() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Screen.Home.route) {
            composable(route = Screen.Home.route) {
                HomeScreen (
                    navSettings = { navController.navigate(Screen.Settings.route) },
                    navButton = { navController.navigate(Screen.Button.buttonRoute(it, 1)) }
                )
            }

            composable(route = Screen.Settings.route) {
                SettingsScreen(
                    navBack = { navController.popBackStack() },
                    navController = navController
                )
            }

            composable(
                route = Screen.Button.route,
                arguments = listOf(
                    navArgument("id") { type = IntType },
                    navArgument("increment") { type = IntType }
                )
            ) { navBackStackEntry ->
                val id = navBackStackEntry.savedStateHandle.get<Int>("id")
                val increment = navBackStackEntry.savedStateHandle.get<Int>("increment")
                println(id)
                println(increment)
                ButtonScreen(
                    navSettings = { navController.navigate(Screen.Settings.route) },
                    navBack = { navController.popBackStack() },
                    id = id!!,
                    increment = increment!!
                )
            }
        }
    }
}

enum class Screen(val route: String) {
    Home("home"),
    Settings("settings"),
    Button("button/{id}/{increment}");

    fun buttonRoute(id: Int, increment: Int) = "button/$id/$increment"
}