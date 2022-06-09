package id.anantyan.moviesapp.ui.screen.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import id.anantyan.moviesapp.ui.profile.ProfileActivity
import id.anantyan.moviesapp.ui.theme.topAppBarBackgroundColor
import id.anantyan.moviesapp.ui.theme.topAppBarContentColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun homeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val getTrending = viewModel.getTrending().collectAsLazyPagingItems()
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Moviesapp",
                        color = MaterialTheme.colors.topAppBarContentColor()
                    )
                },
                backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor(),
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                    }
                    IconButton(onClick = {
                        val intent = Intent(context, ProfileActivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile Icon"
                        )
                    }
                }
            )
        },
        content = {
            listItem(items = getTrending)
        }
    )
}