package es.usj.poppedcorn

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.usj.poppedcorn.data.MovieApi
import es.usj.poppedcorn.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class SplashActivity : AppCompatActivity() {

    private lateinit var apiService: MovieApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        apiService = RetrofitInstance.retrofit.create(MovieApi::class.java)

        loadDataFromWebService()
    }

    private fun loadDataFromWebService() {
        val call = apiService.getData()

        call.enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful) {
                    val movies = response.body()
                    if (movies != null) {
                        SingletonMovieData.getInstance().setResponseMovies(movies.toMutableList())
                    }
                    proceedToNextActivity()
                } else {
                    Toast.makeText(this@SplashActivity, "Failed to load data from API", Toast.LENGTH_SHORT).show()
                    Timber.e("Failed to load data from API: ${response.message()}")
                    proceedToNextActivity()
                }
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Toast.makeText(this@SplashActivity, "Failed to load data from API", Toast.LENGTH_SHORT).show()
                Timber.e("Failed to load data from API: ${t.message}")
                proceedToNextActivity()
            }
        })
    }
    private fun proceedToNextActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, MovieListActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}