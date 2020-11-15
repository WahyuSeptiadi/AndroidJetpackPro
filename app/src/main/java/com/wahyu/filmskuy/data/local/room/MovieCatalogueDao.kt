package com.wahyu.filmskuy.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.data.local.entity.TvShowEntity

/**
 * Created by wahyu_septiadi on 07, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

@Dao
interface MovieCatalogueDao {
    @Query("SELECT * FROM movies_favorite")
    fun getAllMovie(): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovieList(movieEntity: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(movieEntity: MovieEntity)

    @Query("DELETE FROM movies_favorite WHERE id = :id")
    fun deleteMovieWithId(id: Int)

    @Query("SELECT * FROM tv_shows_favorite")
    fun getAllTvShow(): LiveData<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTvShow(tvShowEntity: TvShowEntity)

    @Delete
    fun deleteTvShow(tvShowEntity: TvShowEntity)
}