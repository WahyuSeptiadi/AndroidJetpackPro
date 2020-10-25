package com.wahyu.filmskuy.viewmodels

import androidx.lifecycle.ViewModel
import com.wahyu.filmskuy.models.MovieModel
import com.wahyu.filmskuy.utils.DataDummy

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieViewModel: ViewModel() {
    fun getMovies(): List<MovieModel> = DataDummy.generateDummyMovies()
}