package com.wahyu.filmskuy.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wahyu.filmskuy.models.FilmCatalogue
import com.wahyu.filmskuy.repository.MovieRepository
import java.util.ArrayList

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieViewModel: ViewModel() {

    private var listSearchMovies = MutableLiveData<ArrayList<FilmCatalogue>>()

    fun loadMovies(): LiveData<ArrayList<FilmCatalogue>> = MovieRepository().getAllDataMovies()

    fun searchMovies(title: String): LiveData<ArrayList<FilmCatalogue>> {
        listSearchMovies =
            MovieRepository().setTitleSearchMovie(title) as MutableLiveData<ArrayList<FilmCatalogue>>

        return listSearchMovies
    }
}