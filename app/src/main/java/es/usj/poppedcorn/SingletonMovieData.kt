package es.usj.poppedcorn

import es.usj.poppedcorn.model.Movie

class SingletonMovieData private constructor() {
    private var responseMovies: MutableList<Movie> = mutableListOf() // Initialize with an empty mutable list

    fun setResponseMovies(movies: MutableList<Movie>?) {
        responseMovies = movies ?: mutableListOf() // Use the provided list if not null, otherwise use an empty mutable list
    }

    fun getResponseMovies(): MutableList<Movie> {
        return responseMovies
    }

    fun addMovie(movie: Movie) {
        responseMovies.add(movie)
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
