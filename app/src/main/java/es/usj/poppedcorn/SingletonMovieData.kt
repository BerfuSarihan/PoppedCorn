package es.usj.poppedcorn

import es.usj.poppedcorn.model.Movie

class SingletonMovieData private constructor() {
    private var responseMovies: MutableList<Movie>? = null

    fun setResponseMovies(movies: MutableList<Movie>?) {
        responseMovies = movies
    }

    fun getResponseMovies(): MutableList<Movie>? {
        return responseMovies
    }

    fun addMovie(movie: Movie) {
        responseMovies?.add(movie)
    }

    companion object {
        private var instance: SingletonMovieData? = null

        fun getInstance(): SingletonMovieData {
            if (instance == null) {
                instance = SingletonMovieData()
            }
            return instance!!
        }
    }
}