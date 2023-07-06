package es.usj.poppedcorn
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import es.usj.poppedcorn.databinding.ActivityMovieListBinding
import es.usj.poppedcorn.model.Movie

class MovieListActivity : AppCompatActivity() {

    private lateinit var view: ActivityMovieListBinding
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(view.root)

        val listView: ListView = view.listViewMovies
        movieAdapter = MovieAdapter(this)
        listView.adapter = movieAdapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedMovie = movieAdapter.getItem(position) as? Movie
            if (selectedMovie != null) {
                val intent = Intent(this, ViewMovieActivity::class.java)
                intent.putExtra("movieId", selectedMovie.movieId)
                startActivity(intent)
            }
        }

        searchView = view.viewsearch
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                movieAdapter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                movieAdapter.filter(newText)
                return true
            }
        })

        view.btncontact.setOnClickListener {
            val intent = Intent(this, ContactActivity::class.java)
            startActivity(intent)
        }

        view.floatingActionButton3.setOnClickListener {
            val intent = Intent(this, AddMovieActivity::class.java)
            startActivity(intent)
        }
    }
}