package com.wahyu.filmskuy.views.activity

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.models.MovieModel
import com.wahyu.filmskuy.utils.DataDummy
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIES = "extra_movies"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val extras = intent.extras
        if (extras != null){
            val movieId = extras.getString(EXTRA_MOVIES)
            if (movieId != null){
                for (movie in DataDummy.generateDummyMovies()) {
                    if (movie.moviesId == movieId){
                        getDataMovie(movie)
                    }
                }
            }
        }

        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getDataMovie(movieModel: MovieModel) {
        Picasso.get().load(movieModel.image).into(backgroundDetailFilm)
        Picasso.get().load(movieModel.image).into(imageDetailFilm)

        titleDetailFilm.text = movieModel.title
        releaseDetailFilm.text = movieModel.release
        ratingDetailFilm.text = movieModel.rating
        overviewDetailFilm.text = movieModel.overview

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                overviewDetailFilm.justificationMode = JUSTIFICATION_MODE_INTER_WORD
            }
        }
    }
}