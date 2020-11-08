package com.wahyu.filmskuy.data.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.wahyu.filmskuy.data.local.entity.TvShowEntity

/**
 * Created by wahyu_septiadi on 07, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tv_shows_favorite")
    fun getAll(): DataSource.Factory<Int, TvShowEntity>

    @Insert
    fun insert(movieEntity: TvShowEntity)

    @Delete
    fun delete(movieEntity: TvShowEntity)
}