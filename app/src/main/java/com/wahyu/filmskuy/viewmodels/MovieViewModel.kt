package com.wahyu.filmskuy.viewmodels

import androidx.lifecycle.ViewModel
import com.wahyu.filmskuy.repository.MovieRepository

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieViewModel : ViewModel() {
    val movies = MovieRepository.getMovies()
}