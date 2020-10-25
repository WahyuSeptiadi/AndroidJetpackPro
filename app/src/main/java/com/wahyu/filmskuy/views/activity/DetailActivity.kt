package com.wahyu.filmskuy.views.activity

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.models.FilmModel
import com.wahyu.filmskuy.utils.DataDummy
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIES = "extra_films"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val extras = intent.extras
        if (extras != null){
            val filmId = extras.getString(EXTRA_MOVIES)
            if (filmId != null){
                if (filmId.substring(0,1) == "m"){
                    for (film in DataDummy.generateDummyMovies()) {
                        if (film.id == filmId){
                            getDataFilm(film)
                        }
                    }
                }else{
                    for (film in DataDummy.generateDummyTvShow()) {
                        if (film.id == filmId){
                            getDataFilm(film)
                        }
                    }
                }
            }
        }

        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getDataFilm(filmModel: FilmModel) {
        Picasso.get().load(filmModel.image).into(backgroundDetailFilm)
        Picasso.get().load(filmModel.image).into(imageDetailFilm)

        titleDetailFilm.text = filmModel.title
        releaseDetailFilm.text = filmModel.release
        ratingDetailFilm.text = filmModel.rating
        overviewDetailFilm.text = filmModel.overview

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                overviewDetailFilm.justificationMode = JUSTIFICATION_MODE_INTER_WORD
            }
        }
    }
}