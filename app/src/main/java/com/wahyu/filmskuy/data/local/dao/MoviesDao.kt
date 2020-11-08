package com.wahyu.filmskuy.data.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.wahyu.filmskuy.data.local.entity.MovieEntity


/**
 * Created by wahyu_septiadi on 07, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies_favorite WHERE isFavorite = 1")
    fun getAll(): DataSource.Factory<Int, MovieEntity>

    @Insert
    fun insert(movieEntity: MovieEntity)

    @Delete
    fun delete(movieEntity: MovieEntity)
}