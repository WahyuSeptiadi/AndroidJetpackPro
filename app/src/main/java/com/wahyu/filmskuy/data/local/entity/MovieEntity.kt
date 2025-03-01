package com.wahyu.filmskuy.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by wahyu_septiadi on 07, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

@Entity(tableName = "movies_favorite")
data class MovieEntity(
    val popular: Boolean? = null,
    val favorite: Boolean,
    @PrimaryKey
    val id: Int,
    val overview: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null,
    @ColumnInfo(name = "release_date")
    val releaseDate: String? = null,
    val title: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double
)