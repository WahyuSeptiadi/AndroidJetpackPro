package com.wahyu.filmskuy.repository.local

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.wahyu.filmskuy.data.local.room.MovieCatalogueDatabase
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.data.local.room.MovieCatalogueDao
import java.util.concurrent.Executors

/**
 * Created by wahyu_septiadi on 07, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieFavoriteRepository(val context: Context) {
    private val movieCatalogueDao : MovieCatalogueDao
    private val executorService = Executors.newSingleThreadExecutor()

    init {
        val db = MovieCatalogueDatabase.getInstance(context)
        movieCatalogueDao = db.filmCatalogueDao()
    }

    fun getAllMovie(): LiveData<List<MovieEntity>> = movieCatalogueDao.getAllMovie()

    fun insertMovie(movieEntity: MovieEntity) {
        executorService.execute {
            try {
                movieCatalogueDao.insertMovie(movieEntity)
            } catch (e: Exception) {
                Log.e("INSERT FAILED", e.message.toString())
            }
        }
    }

    fun deleteMovieWithId(id: Int) {
        executorService.execute { movieCatalogueDao.deleteMovieWithId(id) }
    }
}