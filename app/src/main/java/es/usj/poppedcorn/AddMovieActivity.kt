package es.usj.poppedcorn

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.usj.poppedcorn.data.MovieApi
import es.usj.poppedcorn.databinding.ActivityAddMovieBinding
import es.usj.poppedcorn.model.Movie

class AddMovieActivity : AppCompatActivity() {

    private lateinit var view: ActivityAddMovieBinding
    private lateinit var apiService: MovieApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityAddMovieBinding.inflate(layoutInflater)
        setContentView(view.root)


        val btnSaveMovie: Button = view.btnSaveMovie
        btnSaveMovie.setOnClickListener {
            saveMovie()
        }
    }

    private fun saveMovie() {
        val id = view.etId.text.toString().trim()
        val title = view.etTitle.text.toString().trim()
        val description = view.etDescription.text.toString().trim()
        val yearString = view.etYear.text.toString().trim()
        val ratingString = view.etRating.text.toString().trim()

        if (title.isEmpty() || description.isEmpty() || yearString.isEmpty() || ratingString.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val year = yearString.toIntOrNull()
        val rating = ratingString.toDoubleOrNull()

        if (year == null || rating == null)
        {
            Toast.makeText(this, "Invalid year or rating", Toast.LENGTH_SHORT).show()
            return
        }

        val movie = Movie(movieId = id, title = title, description = description, year = year, rating = rating)

        val singleton = SingletonMovieData.getInstance()
        val movies = singleton.getResponseMovies()

        if (movies != null) {
            if (movies.any { it.title == title }) {
                // Movie with the same title already exists
                Toast.makeText(this, "$title already exists", Toast.LENGTH_SHORT).show()
            } else {
                singleton.addMovie(movie)
                Toast.makeText(this, "$title added successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MovieListActivity::class.java)
                startActivity(intent)
            }
        }

    }
}
