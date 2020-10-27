package com.wahyu.filmskuy.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wahyu.filmskuy.models.FilmModel
import com.wahyu.filmskuy.repository.TvShowRepository

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class TvShowViewModel : ViewModel() {
    var tvShows: MutableLiveData<List<FilmModel>>? = null
        get() {
            if (field == null) {
                Log.d("MovieViewModel", "${loadTvShows()}")
                val data = MutableLiveData<List<FilmModel>>()
                data.value = loadTvShows()
                field = data
            }
            return field
        }
        private set

    fun loadTvShows(): List<FilmModel> = TvShowRepository.getTvShows()
}