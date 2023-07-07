package es.usj.poppedcorn


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.usj.poppedcorn.databinding.ActivityEditMovieBinding

class EditMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ActivityEditMovieBinding.inflate(layoutInflater)
        setContentView(view.root)
}}