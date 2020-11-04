package com.wahyu.filmskuy.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by wahyu_septiadi on 29, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

@Parcelize
data class DetailFilmCatalogue(
    val id: Int? = 0,
    val image: String? = null,
    val title: String? = null,
    val overview: String? = null,
    val vote: Double? = null,
    val release: String? = null
) : Parcelable