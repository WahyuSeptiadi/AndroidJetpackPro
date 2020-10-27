package com.wahyu.filmskuy.viewmodels

import androidx.lifecycle.ViewModel
import com.wahyu.filmskuy.repository.MovieRepository

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieViewModel : ViewModel() {
    val movies = MovieRepository.getMovies()

//    var movies: MutableLiveData<List<FilmModel>>? = null
//        get() {
//            if (field == null) {
//                field = MutableLiveData<List<FilmModel>>()
//                loadMovies()
//            }
//            return field
//        }
//        private set
//
//    private fun loadMovies() {
//        MovieRepository.getMovies()
//    }
}