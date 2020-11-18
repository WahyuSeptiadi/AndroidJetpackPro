package com.wahyu.filmskuy.viewmodels.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.wahyu.filmskuy.data.local.entity.TvShowEntity
import com.wahyu.filmskuy.repository.MovieCatalogueRepository

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class TvShowViewModel(private val data: MovieCatalogueRepository) : ViewModel() {
    private val percent = "%"

    fun getAllTvShowPopular(): LiveData<PagedList<TvShowEntity>> =
        data.getAllTvShowPopular()

    fun getAllTvShowByName(name: String): LiveData<PagedList<TvShowEntity>> =
        data.getSearchTvShowByName(percent + name + percent)
}