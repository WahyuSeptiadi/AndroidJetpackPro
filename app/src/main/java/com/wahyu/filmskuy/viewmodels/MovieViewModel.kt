package com.wahyu.filmskuy.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wahyu.filmskuy.models.FilmModel
import com.wahyu.filmskuy.repository.MovieRepository

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieViewModel : ViewModel() {
    var movies: MutableLiveData<List<FilmModel>>? = null
        get() {
            if (field == null) {
                Log.d("MovieViewModel", "${loadMovies()}")
                val data = MutableLiveData<List<FilmModel>>()
                data.value = loadMovies()
                field = data
            }
            return field
        }
        private set

    fun loadMovies(): List<FilmModel> = MovieRepository().getMovies()
}