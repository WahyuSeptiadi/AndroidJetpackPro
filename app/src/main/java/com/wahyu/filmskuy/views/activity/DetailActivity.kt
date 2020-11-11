package com.wahyu.filmskuy.views.activity

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.models.FilmCatalogueModel
import com.wahyu.filmskuy.utils.IMAGE_URL_BASE_PATH
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

        val film =
            intent.getParcelableExtra<FilmCatalogueModel>(EXTRA_FILMS) as FilmCatalogueModel

        if (film.image != null) {
            val imageSize = getString(R.string.size_url_image_detail)
            val urlImage = "$IMAGE_URL_BASE_PATH$imageSize${film.image}"
            Glide.with(this).load(urlImage).placeholder(R.drawable.loading)
                .into(backgroundDetailFilm)
            Glide.with(this).load(urlImage).placeholder(R.drawable.loading)
                .into(imageDetailFilm)
        } else {
            Glide.with(this).load(R.drawable.img_notfound).placeholder(R.drawable.loading)
                .into(backgroundDetailFilm)
            Glide.with(this).load(R.drawable.img_notfound).placeholder(R.drawable.loading)
                .into(imageDetailFilm)
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