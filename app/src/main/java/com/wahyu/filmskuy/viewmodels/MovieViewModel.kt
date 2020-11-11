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

    //implementasi viewmodel masih belum tepat, seharusnya ketika device di rotate data masih ada (tidak memuat ulang)
    fun getMovies() : LiveData<MutableList<MovieResult>>? {
        return data.getAllMovies()
    }

    fun setMovie(title: String) : LiveData<MutableList<MovieResult>> {
        return data.searchMovie(title)
    }
}