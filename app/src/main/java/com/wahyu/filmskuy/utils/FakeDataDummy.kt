package com.wahyu.filmskuy.utils

import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.models.FilmCatalogue

/**
 * Created by wahyu_septiadi on 04, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

object FakeDataDummy {
//    fun generateDummyMovies(): List<FilmCatalogue> {
//        return listOf(
//            FilmCatalogue(
//                id = 475557,
//                image = "$IMAGE_URL_BASE_PATH/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
//                title = "Joker",
//                overview = "hello world",
//                vote = 8.4,
//                release = "2019-10-02"
//            )
//        )
//    }

    fun generateDummyMovies(): List<FilmCatalogue> {
        val films = ArrayList<FilmCatalogue>()

        val imageUrl = "ugZW8ocsrfgI95pnQ7wrmKDxIe.jpg"

        films.add(
            FilmCatalogue(
                724989,
                "$IMAGE_URL_BASE_PATH$imageUrl",
                "Hard Kill",
                "The work of billionaire tech CEO Donovan Chalmers is so valuable that he hires mercenaries to protect it, and a terrorist group kidnaps his daughter just to get it.",
                4.3,
                "2020-10-23"
            )
        )
        return films
    }
}