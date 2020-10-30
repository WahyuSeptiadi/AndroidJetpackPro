package com.wahyu.filmskuy.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by wahyu_septiadi on 29, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

@Parcelize
data class MovieCatalogue(
    val id: Int,
    val image: String,
    val title: String,
    val overview: String,
    val vote: Double,
    val release: String
) : Parcelable