package com.wahyu.filmskuy.models

/**
 * Created by wahyu_septiadi on 22, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

data class FilmModel(
    val id: String,
    val image: Int,
    val title: String,
    val overview: String,
    val rating: String,
    val release: String,
    val year: String
)