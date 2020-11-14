package com.wahyu.filmskuy.viewmodels.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.repository.local.MovieFavoriteRepository

/**
 * Created by wahyu_septiadi on 07, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieFavoriteViewModel(context: Context) : ViewModel() {
    private val movieFavoriteRepository = MovieFavoriteRepository(context)

    fun getAllMovie(): LiveData<List<MovieEntity>> = movieFavoriteRepository.getAllMovie()


    fun insertMovie(movieEntity: MovieEntity) {
        movieFavoriteRepository.insertMovie(movieEntity)
    }

    fun deleteMovieWithId(id: Int) {
        movieFavoriteRepository.deleteMovieWithId(id)
    }
}