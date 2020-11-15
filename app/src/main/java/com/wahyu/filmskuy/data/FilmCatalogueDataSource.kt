package com.wahyu.filmskuy.data

import androidx.lifecycle.LiveData
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.vo.Resources

/**
 * Created by wahyu_septiadi on 14, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

interface FilmCatalogueDataSource {
    fun getAllMovie(): LiveData<Resources<List<MovieEntity>>>

    fun searchMovie(title: String): LiveData<Resources<List<MovieEntity>>>
}