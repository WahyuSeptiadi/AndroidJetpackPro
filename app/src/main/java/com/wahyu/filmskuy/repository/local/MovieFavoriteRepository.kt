package com.wahyu.filmskuy.repository.local

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.wahyu.filmskuy.data.local.room.FilmCatalogueDatabase
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.data.local.room.FilmCatalogueDao
import java.util.concurrent.Executors

/**
 * Created by wahyu_septiadi on 07, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieFavoriteRepository(val context: Context) {
    private val filmCatalogueDao : FilmCatalogueDao
    private val executorService = Executors.newSingleThreadExecutor()

    init {
        val db = FilmCatalogueDatabase.getDatabase(context)
        filmCatalogueDao = db.filmCatalogueDao()
    }

    fun getAllMovie(): LiveData<List<MovieEntity>> = filmCatalogueDao.getAllMovie()

    fun insertMovie(movieEntity: MovieEntity) {
        executorService.execute {
            try {
                filmCatalogueDao.insertMovie(movieEntity)
            } catch (e: Exception) {
                Log.e("INSERT FAILED", e.message.toString())
            }
        }
    }

    fun deleteMovie(movieEntity: MovieEntity) {
        executorService.execute { filmCatalogueDao.deleteMovie(movieEntity) }
    }
}