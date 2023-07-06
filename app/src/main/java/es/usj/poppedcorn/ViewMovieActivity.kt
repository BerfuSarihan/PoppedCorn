package es.usj.poppedcorn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.usj.poppedcorn.databinding.ActivityViewMovieBinding
import es.usj.poppedcorn.model.Movie

class ViewMovieActivity : AppCompatActivity() {

    private lateinit var view: ActivityViewMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityViewMovieBinding.inflate(layoutInflater)
        setContentView(view.root)

        val movieId = intent.getStringExtra("movieId")

        val movie = getMovieDetails(movieId)
        if (movie != null) {
            displayMovieDetails(movie)
        }
    }

    private fun getMovieDetails(movieId: String?): Movie? {
        val movies = SingletonMovieData.getInstance().getResponseMovies()
        return movies?.find { it.movieId == movieId }
    }

    private fun displayMovieDetails(movie: Movie) {
        view.textViewTitle.text = movie.title
        view.textViewYear.text = "Year: ${movie.year}"
        view.textViewDirector.text = "Director: ${movie.director}"
        view.textViewRating.text = "Rating: ${movie.rating}"
        view.textViewVotes.text = "Votes: ${movie.votes}"
        view.textViewActors.text = "Actors: ${movie.actors?.joinToString(", ")}"
        view.textViewDescription1.text = movie.description
    }
}
