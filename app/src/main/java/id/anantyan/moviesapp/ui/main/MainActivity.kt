package id.anantyan.moviesapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import id.anantyan.moviesapp.ui.navigation.setupNavGraph
import id.anantyan.moviesapp.ui.theme.MoviesappTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesappTheme {
                val navController = rememberNavController()
                setupNavGraph(navHostController = navController)
            }
        }
    }
}