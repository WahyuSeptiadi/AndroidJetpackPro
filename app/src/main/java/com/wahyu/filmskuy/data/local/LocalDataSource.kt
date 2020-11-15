package com.wahyu.filmskuy.data.local

import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.data.local.room.FilmCatalogueDao

/**
 * Created by wahyu_septiadi on 14, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class LocalDataSource private constructor(private val filmCatalogueDao: FilmCatalogueDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmCatalogueDao: FilmCatalogueDao): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(filmCatalogueDao)
            }
            return INSTANCE as LocalDataSource
        }
    }

    fun getAllMovieDB() = filmCatalogueDao.getAllMovie()

    fun insertMovie(movie: List<MovieEntity>) = filmCatalogueDao.insertMovieList(movie)
}