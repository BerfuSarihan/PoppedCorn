package es.usj.poppedcorn

import es.usj.poppedcorn.model.Movie


class SingletonMovieData private constructor() {
    private var responseMovies: List<Movie>? = null

    fun setResponseMovies(movies: List<Movie>?) {
        responseMovies = movies
    }

    fun getResponseMovies(): List<Movie>? {
        return responseMovies
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