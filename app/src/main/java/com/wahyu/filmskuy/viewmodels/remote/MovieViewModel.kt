package com.wahyu.filmskuy.viewmodels.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.repository.MovieCatalogueRepository

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieViewModel(private val data: MovieCatalogueRepository) : ViewModel() {
    private val percent = "%"

    fun getAllMoviePopular(): LiveData<PagedList<MovieEntity>> =
        data.getAllMoviePopular()

    fun getAllMovieByTitle(title: String): LiveData<PagedList<MovieEntity>> =
        data.getSearchMovieByTitle(percent + title + percent)
}