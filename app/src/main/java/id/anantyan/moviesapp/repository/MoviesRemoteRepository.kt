package id.anantyan.moviesapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import id.anantyan.moviesapp.data.api.MoviesApi
import javax.inject.Inject

class MoviesRemoteRepository @Inject constructor(
    private val moviesApi: MoviesApi
) {

    fun getTrendingWeek() = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            MoviesPagingSource(moviesApi)
        }
    ).flow
}