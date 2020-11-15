package com.wahyu.filmskuy.repository.local

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.wahyu.filmskuy.data.local.entity.TvShowEntity
import com.wahyu.filmskuy.data.local.room.MovieCatalogueDao
import com.wahyu.filmskuy.data.local.room.MovieCatalogueDatabase
import java.util.concurrent.Executors

/**
 * Created by wahyu_septiadi on 11, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class TvShowFavoriteRepository(val context: Context) {
    private val movieCatalogueDao : MovieCatalogueDao
    private val executorService = Executors.newSingleThreadExecutor()

    init {
        val db = MovieCatalogueDatabase.getInstance(context)
        movieCatalogueDao = db.filmCatalogueDao()
    }

    fun getAllTvShow(): LiveData<List<TvShowEntity>> = movieCatalogueDao.getAllTvShow()

    fun insertTvShow(tvShowEntity: TvShowEntity) {
        executorService.execute {
            try {
                movieCatalogueDao.insertTvShow(tvShowEntity)
            } catch (e: Exception) {
                Log.e("INSERT FAILED", e.message.toString())
            }
        }
    }

    fun deleteTvShow(tvShowEntity: TvShowEntity) {
        executorService.execute { movieCatalogueDao.deleteTvShow(tvShowEntity) }
    }
}