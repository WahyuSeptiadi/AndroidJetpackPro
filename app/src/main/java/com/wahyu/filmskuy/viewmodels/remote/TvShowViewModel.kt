package com.wahyu.filmskuy.viewmodels.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.wahyu.filmskuy.data.remote.response.TvShowResult
import com.wahyu.filmskuy.repository.remote.TvShowRepository

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class TvShowViewModel(private val data: TvShowRepository) : ViewModel() {
    fun getTvShows(): LiveData<MutableList<TvShowResult>> {
        return data.getAllTvShows()
    }

    fun setTvShow(title: String): LiveData<MutableList<TvShowResult>> {
        return data.searchTvShow(title)
    }
}