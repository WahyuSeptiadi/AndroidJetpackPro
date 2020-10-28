package com.wahyu.filmskuy.repository

import com.wahyu.filmskuy.models.FilmModel
import com.wahyu.filmskuy.utils.DataDummy

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieRepository {
    //Sebaiknya kamu tidak mendeklarasikan fungsi mengambil data berikut pada Companion Object.
    //Hal ini membuat fungsi tersebut menjadi static dan bukan merupakan sebuah instance dari objek.
    companion object {
        fun getMovies(): List<FilmModel> = DataDummy.generateDummyMovies()
    }
}