package com.wahyu.filmskuy.data.remote.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by wahyu_septiadi on 29, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

data class TvShowResponse(
    val page: Int,
    val results: List<TvShowResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("totalResults")
    val totalResults: Int
)

@Parcelize
data class TvShowResult(
    @SerializedName("first_air_date")
    val firstAirDate: String? = null,
    val id: Int,
    val name: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double
) : Parcelable