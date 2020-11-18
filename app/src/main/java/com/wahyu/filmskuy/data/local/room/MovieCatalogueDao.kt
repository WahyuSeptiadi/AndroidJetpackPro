package com.wahyu.filmskuy.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.data.local.entity.TvShowEntity

/**
 * Created by wahyu_septiadi on 07, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

@Dao
interface MovieCatalogueDao {
    @Query("SELECT * FROM movies_favorite WHERE favorite = 1")
    fun getAllMovieFavorite(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movies_favorite WHERE title LIKE :title")
    fun getSearchMovieByTitle(title: String): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movies_favorite WHERE popular = 1")
    fun getAllMoviePopular(): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllMovieFromAPI(movieEntity: ArrayList<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovieDB(movieEntity: MovieEntity)

    @Query("SELECT * FROM tv_shows_favorite WHERE favorite = 1")
    fun getAllTvShowFavorite(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tv_shows_favorite WHERE name LIKE :name")
    fun getSearchTvShowByName(name: String): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tv_shows_favorite WHERE popular = 1")
    fun getAllTvShowPopular(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllTvShowFromAPI(tvShowEntity: ArrayList<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateTvShowDB(tvShowEntity: TvShowEntity)
}