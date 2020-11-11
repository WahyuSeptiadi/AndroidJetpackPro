package com.wahyu.filmskuy.viewmodels.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.wahyu.filmskuy.data.remote.response.MovieResult
import com.wahyu.filmskuy.repository.remote.MovieRepository

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