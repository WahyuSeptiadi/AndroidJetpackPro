package com.wahyu.filmskuy.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wahyu.filmskuy.models.FilmCatalogue
import com.wahyu.filmskuy.repository.TvShowRepository
import java.util.ArrayList

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class TvShowViewModel : ViewModel() {

    private var listSearchTvShows = MutableLiveData<ArrayList<FilmCatalogue>>()

    fun loadTvShows(): LiveData<ArrayList<FilmCatalogue>> = TvShowRepository().getAllDataTvShows()

    fun searchTvShow(title: String): LiveData<ArrayList<FilmCatalogue>> {
        listSearchTvShows =
            TvShowRepository().setTitleSearchTvShow(title) as MutableLiveData<ArrayList<FilmCatalogue>>

        return listSearchTvShows
    }
}