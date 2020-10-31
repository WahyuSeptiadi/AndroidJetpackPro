package com.wahyu.filmskuy.views.activity

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.models.FilmCatalogue
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_FILMS = "extra_films"
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

        val film = intent.getParcelableExtra<FilmCatalogue>(EXTRA_FILMS) as FilmCatalogue

        if (film.image != null) {
            val imageSize = "w500"
            val urlImage = "https://image.tmdb.org/t/p/$imageSize${film.image}"
            Picasso.get().load(urlImage).into(backgroundDetailFilm)
            Picasso.get().load(urlImage).into(imageDetailFilm)
        } else {
            Picasso.get().load(R.drawable.img_notfound).into(backgroundDetailFilm)
            Picasso.get().load(R.drawable.img_notfound).into(imageDetailFilm)
        }

        titleDetailFilm.text = film.title
        releaseDetailFilm.text = film.release
        ratingDetailFilm.text = film.vote.toString()
        overviewDetailFilm.text = film.overview

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                overviewDetailFilm.justificationMode = JUSTIFICATION_MODE_INTER_WORD
            }
        }
    }
}