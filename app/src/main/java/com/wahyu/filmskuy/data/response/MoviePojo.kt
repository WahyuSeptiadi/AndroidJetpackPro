package com.wahyu.filmskuy.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by wahyu_septiadi on 29, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

data class MovieResponse(
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)

@Parcelize
data class MovieResult(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String? = null,
    val release_date: String? = null,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) : Parcelable