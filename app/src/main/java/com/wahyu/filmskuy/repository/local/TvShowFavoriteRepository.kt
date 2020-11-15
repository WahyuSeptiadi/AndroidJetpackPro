package com.wahyu.filmskuy.repository.local

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.wahyu.filmskuy.data.local.entity.TvShowEntity
import com.wahyu.filmskuy.data.local.room.FilmCatalogueDao
import com.wahyu.filmskuy.data.local.room.FilmCatalogueDatabase
import java.util.concurrent.Executors

/**
 * Created by wahyu_septiadi on 11, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class TvShowFavoriteRepository(val context: Context) {
    private val filmCatalogueDao : FilmCatalogueDao
    private val executorService = Executors.newSingleThreadExecutor()

    init {
        val db = FilmCatalogueDatabase.getInstance(context)
        filmCatalogueDao = db.filmCatalogueDao()
    }

    fun getAllTvShow(): LiveData<List<TvShowEntity>> = filmCatalogueDao.getAllTvShow()

    fun insertTvShow(tvShowEntity: TvShowEntity) {
        executorService.execute {
            try {
                filmCatalogueDao.insertTvShow(tvShowEntity)
            } catch (e: Exception) {
                Log.e("INSERT FAILED", e.message.toString())
            }
        }
    }

    fun deleteTvShow(tvShowEntity: TvShowEntity) {
        executorService.execute { filmCatalogueDao.deleteTvShow(tvShowEntity) }
    }
}