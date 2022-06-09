package id.anantyan.moviesapp.ui.screen.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.anantyan.moviesapp.repository.MoviesRemoteRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MoviesRemoteRepository
) : ViewModel() {
    fun getTrending() = repository.getTrendingWeek()
}