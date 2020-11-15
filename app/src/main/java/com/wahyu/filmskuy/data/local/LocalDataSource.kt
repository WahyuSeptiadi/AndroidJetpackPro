package com.wahyu.filmskuy.data.local

import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.data.local.room.MovieCatalogueDao

/**
 * Created by wahyu_septiadi on 14, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class LocalDataSource private constructor(private val movieCatalogueDao: MovieCatalogueDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieCatalogueDao: MovieCatalogueDao): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(movieCatalogueDao)
            }
            return INSTANCE as LocalDataSource
        }
    }

    fun getAllMovieDB() = movieCatalogueDao.getAllMovie()

    fun insertMovie(movie: List<MovieEntity>) = movieCatalogueDao.insertMovieList(movie)
}