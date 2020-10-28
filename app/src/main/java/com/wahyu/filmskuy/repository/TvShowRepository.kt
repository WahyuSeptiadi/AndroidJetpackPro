package com.wahyu.filmskuy.repository

import com.wahyu.filmskuy.models.FilmModel
import com.wahyu.filmskuy.utils.DataDummy

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class TvShowRepository {
    fun getTvShows(): List<FilmModel> = DataDummy.generateDummyTvShow()
}