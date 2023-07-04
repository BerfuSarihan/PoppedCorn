package es.usj.poppedcorn.data

import retrofit2.Call
import es.usj.poppedcorn.model.Movie
import retrofit2.http.*

interface MovieApi {
    @GET("/movies")
    fun getData(): Call<List<Movie>>

    @GET("movie/{id}")
    fun getMovieById(@Path("movieId") id: Int): Call<Movie>

    @POST("/movies")
    fun addMovie(@Body movie: Movie): Call<Movie>

    @PUT("movie/{id}")
    fun updateMovie(@Path("movieId") id: Int, @Body movie: Movie): Call<Movie>
}

