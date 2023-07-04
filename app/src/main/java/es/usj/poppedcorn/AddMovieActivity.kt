package es.usj.poppedcorn

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.usj.poppedcorn.databinding.ActivityAddMovieBinding
import es.usj.poppedcorn.data.MovieApi
import es.usj.poppedcorn.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddMovieActivity : AppCompatActivity() {

    private lateinit var view: ActivityAddMovieBinding
    private lateinit var apiService: MovieApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityAddMovieBinding.inflate(layoutInflater)
        setContentView(view.root)

        apiService = RetrofitInstance.retrofit.create(MovieApi::class.java)

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

        val call = apiService.addMovie(movie)

        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>)
            {
                if (response.isSuccessful)
                {
                    Toast.makeText(this@AddMovieActivity, "Movie added successfully", Toast.LENGTH_SHORT).show()
                    finish()
                }
                else
                {
                    Toast.makeText(this@AddMovieActivity, "Failed to add movie", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable)
            {
                Toast.makeText(this@AddMovieActivity, "Failed to add movie", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
