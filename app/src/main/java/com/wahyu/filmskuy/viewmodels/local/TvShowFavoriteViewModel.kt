package com.wahyu.filmskuy.viewmodels.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.wahyu.filmskuy.data.local.entity.TvShowEntity
import com.wahyu.filmskuy.repository.MovieCatalogueRepository

/**
 * Created by wahyu_septiadi on 11, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class TvShowFavoriteViewModel(context: Context) : ViewModel() {
    private val movieCatalogueRepository = MovieCatalogueRepository(context)

    fun getOnlyTvShowFavorite(): LiveData<PagedList<TvShowEntity>> =
        movieCatalogueRepository.getOnlyTvShowFavorite()

    fun updateTvShowToFavorite(movieEntity: TvShowEntity) {
        movieCatalogueRepository.updateTvShowFavorite(movieEntity)
    }
}