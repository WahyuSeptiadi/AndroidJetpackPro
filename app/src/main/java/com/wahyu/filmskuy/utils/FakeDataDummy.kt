package com.wahyu.filmskuy.utils

import com.wahyu.filmskuy.models.FilmCatalogue

/**
 * Created by wahyu_septiadi on 04, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

object FakeDataDummy {
    fun generateDummyMovies(): List<FilmCatalogue> {
        val films = ArrayList<FilmCatalogue>()
        films.add(
            FilmCatalogue(
                724989,
                "$IMAGE_URL_BASE_PATH/ugZW8ocsrfgI95pnQ7wrmKDxIe.jpg",
                "Hard Kill",
                "The work of billionaire tech CEO Donovan Chalmers is so valuable that he hires mercenaries to protect it, and a terrorist group kidnaps his daughter just to get it.",
                4.3,
                "2020-10-23"
            )
        )
        return films
    }
}