package id.anantyan.moviesapp.ui.navigation

sealed class Screen(
    val route: String
) {
    class RoutingTo(screen: String): Screen(route = screen)
}
