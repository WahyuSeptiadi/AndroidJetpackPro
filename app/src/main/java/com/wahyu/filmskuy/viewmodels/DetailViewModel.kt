package com.wahyu.filmskuy.viewmodels

import androidx.lifecycle.ViewModel
import com.wahyu.filmskuy.models.FilmModel
import com.wahyu.filmskuy.utils.DataDummy

/**
 * Created by wahyu_septiadi on 26, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class DetailViewModel : ViewModel() {
    private lateinit var filmId: String

    fun setSelectedFilm(id: String) {
        this.filmId = id
    }

    fun getDetailFilm(): FilmModel {
        lateinit var films: FilmModel
        if (filmId.substring(0, 1) == "m") {
            for (movie in DataDummy.generateDummyMovies()) {
                if (movie.id == filmId) {
                    films = movie
                }
            }
        } else {
            for (tvShow in DataDummy.generateDummyTvShow()) {
                if (tvShow.id == filmId) {
                    films = tvShow
                }
            }
        }
        return films
    }
}