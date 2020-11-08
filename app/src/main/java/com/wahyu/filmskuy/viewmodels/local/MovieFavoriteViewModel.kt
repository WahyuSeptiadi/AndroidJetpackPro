package com.wahyu.filmskuy.viewmodels.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.repository.local.MovieFavoriteRepository

/**
 * Created by wahyu_septiadi on 07, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieFavoriteViewModel(context: Context) : ViewModel() {
    private val mMovieFavoriteRepository = MovieFavoriteRepository(context)

    fun getAll(): LiveData<PagedList<MovieEntity>> =
        LivePagedListBuilder(mMovieFavoriteRepository.getAll(), 20).build()

    fun insert(movieEntity: MovieEntity) {
        mMovieFavoriteRepository.insert(movieEntity)
    }

    fun delete(movieEntity: MovieEntity) {
        mMovieFavoriteRepository.delete(movieEntity)
    }
}