package com.wahyu.filmskuy.viewmodels.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wahyu.filmskuy.data.local.entity.TvShowEntity
import com.wahyu.filmskuy.repository.local.TvShowFavoriteRepository

/**
 * Created by wahyu_septiadi on 11, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class TvShowFavoriteViewModel(context: Context) : ViewModel() {
    private val tvShowFavoriteRepository = TvShowFavoriteRepository(context)

    fun getAllTvShow(): LiveData<PagedList<TvShowEntity>> =
        LivePagedListBuilder(tvShowFavoriteRepository.getAllTvShow(), 20).build()

    fun insertTvShow(tvShowEntity: TvShowEntity) {
        tvShowFavoriteRepository.insertTvShow(tvShowEntity)
    }

    fun deleteTvShow(tvShowEntity: TvShowEntity) {
        tvShowFavoriteRepository.deleteTvShow(tvShowEntity)
    }
}