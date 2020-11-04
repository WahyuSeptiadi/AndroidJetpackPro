package com.wahyu.filmskuy.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wahyu.filmskuy.models.FilmCatalogue
import com.wahyu.filmskuy.repository.MovieRepository

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieViewModel : ViewModel() {

    private var listSearchMovies = MutableLiveData<List<FilmCatalogue>>()

    fun loadMovies(): MutableLiveData<List<FilmCatalogue>> = MovieRepository().getAllDataMovies()

    fun searchMovies(title: String): MutableLiveData<List<FilmCatalogue>> {
        listSearchMovies = MovieRepository().setTitleSearchMovie(title)
        return listSearchMovies
    }
}