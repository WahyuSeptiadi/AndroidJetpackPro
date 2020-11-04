package com.wahyu.filmskuy.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.wahyu.filmskuy.data.response.MovieResult
import com.wahyu.filmskuy.repository.MovieRepository

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieViewModel(private val data : MovieRepository) : ViewModel() {

    fun getMovies() : LiveData<MutableList<MovieResult>>? {
        return data.getAllMovies()
    }

    fun setMovie(title: String) : LiveData<MutableList<MovieResult>> {
        return data.searchMovie(title)
    }
}