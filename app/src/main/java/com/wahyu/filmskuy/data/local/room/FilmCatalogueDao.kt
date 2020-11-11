package com.wahyu.filmskuy.data.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.data.local.entity.TvShowEntity

/**
 * Created by wahyu_septiadi on 07, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

@Dao
interface FilmCatalogueDao {
    @Query("SELECT * FROM movies_favorite")
    fun getAllMovie() : DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(movieEntity: MovieEntity)

    @Delete
    fun deleteMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM tv_shows_favorite")
    fun getAllTvShow() : DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTvShow(tvShowEntity: TvShowEntity)

    @Delete
    fun deleteTvShow(tvShowEntity: TvShowEntity)
}