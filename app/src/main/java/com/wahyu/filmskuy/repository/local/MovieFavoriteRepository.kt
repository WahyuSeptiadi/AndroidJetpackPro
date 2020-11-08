package com.wahyu.filmskuy.repository.local

import android.content.Context
import android.util.Log
import androidx.paging.DataSource
import com.wahyu.filmskuy.data.local.database.MoviesDatabase
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import java.util.concurrent.Executors

/**
 * Created by wahyu_septiadi on 07, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieFavoriteRepository(val context: Context) {
    private val mMoviesDao = MoviesDatabase.getDatabase(context).moviesDao()
    private val executorService = Executors.newSingleThreadExecutor()

    fun getAll(): DataSource.Factory<Int, MovieEntity> = mMoviesDao.getAll()

    fun insert(movieEntity: MovieEntity) {
        executorService.execute {
            try {
                mMoviesDao.insert(movieEntity)
            } catch (e: Exception) {
                Log.e("INSERT FAILED", e.message.toString())
            }
        }
    }

    fun delete(movieEntity: MovieEntity) {
        executorService.execute { mMoviesDao.delete(movieEntity) }
    }
}