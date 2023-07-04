package es.usj.poppedcorn

import es.usj.poppedcorn.model.Movie


class MovieData private constructor() {
    private var responseMovies: List<Movie>? = null

    fun setResponseMovies(movies: List<Movie>?) {
        responseMovies = movies
    }

    fun getResponseMovies(): List<Movie>? {
        return responseMovies
    }

    companion object {
        private var instance: MovieData? = null

        fun getInstance(): MovieData {
            if (instance == null) {
                instance = MovieData()
            }
            return instance!!
        }
    }
}