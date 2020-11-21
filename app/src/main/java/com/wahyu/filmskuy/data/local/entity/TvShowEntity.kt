package com.wahyu.filmskuy.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by wahyu_septiadi on 07, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

@Entity(tableName = "tv_shows_favorite")
class TvShowEntity(
    val popular: Boolean? = null,
    val favorite: Boolean,
    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String? = null,
    @PrimaryKey
    val id: Int,
    val name: String,
    val overview: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double
)