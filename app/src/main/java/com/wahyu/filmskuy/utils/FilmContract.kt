package com.wahyu.filmskuy.utils

import androidx.lifecycle.LiveData
import com.wahyu.filmskuy.models.FilmCatalogue
import java.util.ArrayList

/**
 * Created by wahyu_septiadi on 30, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

interface FilmContract {
    interface Movies {
        fun onAttach(view: View)
        fun getMovies()
        fun setMovies(): LiveData<ArrayList<FilmCatalogue>>
        fun searchMovies(title: String)
    }

    interface TvShows {
        fun onAttach(view: View)
        fun getTvShows()
        fun setTvShows(): LiveData<ArrayList<FilmCatalogue>>
        fun searchTvShows(title: String)
    }

    interface View {
        fun showLoading(isLoading: Boolean)
    }
}