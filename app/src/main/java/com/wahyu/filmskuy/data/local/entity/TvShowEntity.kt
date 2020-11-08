package com.wahyu.filmskuy.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by wahyu_septiadi on 07, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

@Entity(tableName = "tv_shows_favorite")
class TvShowEntity(
    val backdrop_path: String?,
    val first_air_date: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
)