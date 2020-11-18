package com.wahyu.filmskuy.viewmodels.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.repository.MovieCatalogueRepository

/**
 * Created by wahyu_septiadi on 07, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieFavoriteViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {

    fun getOnlyMovieFavorite(): LiveData<PagedList<MovieEntity>> =
        movieCatalogueRepository.getOnlyMovieFavorite()

    fun updateMovieToFavorite(movieEntity: MovieEntity) {
        movieCatalogueRepository.updateMovieFavorite(movieEntity)
    }
}