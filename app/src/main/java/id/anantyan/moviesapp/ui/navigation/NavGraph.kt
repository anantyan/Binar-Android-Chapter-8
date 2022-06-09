package id.anantyan.moviesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import id.anantyan.moviesapp.ui.screen.home.homeScreen

@Composable
fun setupNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.RoutingTo("home_screen").route
    ) {
        composable(
            route = Screen.RoutingTo("home_screen").route
        ) {
            homeScreen(navHostController = navHostController)
        }
    }
}