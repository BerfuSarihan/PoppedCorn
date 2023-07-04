package es.usj.poppedcorn

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import es.usj.poppedcorn.databinding.ActivityViewMovieBinding
import es.usj.poppedcorn.model.Movie

class ViewMovieActivity : AppCompatActivity() {

    private lateinit var view: ActivityViewMovieBinding
    private lateinit var tvMovieTitle: TextView
    private lateinit var tvMovieDescription: TextView
    private lateinit var tvMovieRating: TextView
    private lateinit var tvMovieYear: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityViewMovieBinding.inflate(layoutInflater)
        setContentView(view.root)

        tvMovieTitle = view.tvMovieTitle
        tvMovieDescription = view.tvMovieDescription
        tvMovieRating = view.tvMovieRating
        tvMovieYear = view.tvMovieYear

        val movie = intent.getStringExtra("movie") as? Movie

        if (movie != null) {
            tvMovieTitle.text = movie.title
            tvMovieDescription.text = movie.description
            tvMovieRating.text = movie.rating.toString()
            tvMovieYear.text = movie.year.toString()
        }
    }
}
