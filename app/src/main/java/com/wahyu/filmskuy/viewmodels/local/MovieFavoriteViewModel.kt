package com.wahyu.filmskuy.viewmodels.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.repository.MovieCatalogueRepository

/**
 * Created by wahyu_septiadi on 07, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieFavoriteViewModel(context: Context) : ViewModel() {
    private val movieCatalogueRepository = MovieCatalogueRepository(context)

    fun getOnlyMovieFavorite(): LiveData<List<MovieEntity>> =
        movieCatalogueRepository.getOnlyMovieFavorite()

    fun updateMovieToFavorite(movieEntity: MovieEntity) {
        movieCatalogueRepository.updateMovieFavorite(movieEntity)
    }
}