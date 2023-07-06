package es.usj.poppedcorn

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import es.usj.poppedcorn.model.Movie
class MovieAdapter(private val context: Context) : BaseAdapter() {
    private val movies: List<Movie>? = SingletonMovieData.getInstance().getResponseMovies()
    private var filteredMovies: List<Movie>? = movies

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_movie, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val movie = getItem(position) as Movie
        viewHolder.titleTextView.text = movie.title
        viewHolder.descriptionTextView.text = movie.description

        view.setOnClickListener {
            val intent = Intent(context, ViewMovieActivity::class.java)
            intent.putExtra("movieId", movie.movieId)
            context.startActivity(intent)
        }

        // Set click listener for the edit button
        viewHolder.btnEditMovie.setOnClickListener {
            val intent = Intent(context, EditMovieActivity::class.java)
            intent.putExtra("movieId", movie.movieId)
            context.startActivity(intent)
        }
        return view
    }

    fun filter(query: String) {
        filteredMovies = if (query.isEmpty()) {
            movies
        } else {
            movies?.filter { movie ->
                movie.title.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Any {
        return filteredMovies?.get(position) ?: ""
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return filteredMovies?.size ?: 0
    }

    private class ViewHolder(view: View) {
        val titleTextView: TextView = view.findViewById(R.id.textViewTitle)
        val descriptionTextView: TextView = view.findViewById(R.id.textViewDescription)
        val btnEditMovie: Button = view.findViewById(R.id.btnEditMovie)
    }
}
