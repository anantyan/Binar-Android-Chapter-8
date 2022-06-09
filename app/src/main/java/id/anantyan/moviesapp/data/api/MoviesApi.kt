package id.anantyan.moviesapp.data.api

import id.anantyan.moviesapp.model.Movies
import id.anantyan.moviesapp.utils.Constant.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("trending/movie/week")
    suspend fun getByTrendingWeek(
        @Query("api_key") apiKey: String? = API_KEY,
        @Query("page") page: Int? = 1
    ): Response<Movies>
}