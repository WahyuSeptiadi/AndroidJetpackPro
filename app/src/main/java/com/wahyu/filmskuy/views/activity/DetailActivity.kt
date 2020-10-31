package com.wahyu.filmskuy.views.activity

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.models.FilmCatalogue
import com.wahyu.filmskuy.viewmodels.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIES = "extra_movies"
        const val EXTRA_TV_SHOW = "extra_tv_shows"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getDataMovie()

        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getDataMovie() {

        val movie: FilmCatalogue? = intent.getParcelableExtra(EXTRA_MOVIES)

        Picasso.get().load(movie?.image).into(backgroundDetailFilm)
        Picasso.get().load(movie?.image).into(imageDetailFilm)

        titleDetailFilm.text = movie?.title
        releaseDetailFilm.text = movie?.release
        ratingDetailFilm.text = movie?.vote.toString()
        overviewDetailFilm.text = movie?.overview

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                overviewDetailFilm.justificationMode = JUSTIFICATION_MODE_INTER_WORD
            }
        }
    }
}