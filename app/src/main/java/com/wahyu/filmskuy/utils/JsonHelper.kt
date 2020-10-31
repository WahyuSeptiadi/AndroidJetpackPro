package com.wahyu.filmskuy.utils

import com.wahyu.filmskuy.data.response.MovieResult
import com.wahyu.filmskuy.data.response.TvShowResult
import com.wahyu.filmskuy.models.FilmCatalogue

/**
 * Created by wahyu_septiadi on 29, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

fun getMovieMapper(movies: List<MovieResult>?): List<FilmCatalogue> {
    val listFilm: MutableList<FilmCatalogue> = mutableListOf()

    movies?.forEach {
        listFilm.add(
            FilmCatalogue(
                it.id,
                it.poster_path,
                it.title,
                it.overview,
                it.vote_average,
                it.release_date
            )
        )
    }
    return listFilm
}

fun getTvShowMapper(tvShows: List<TvShowResult>?): MutableList<FilmCatalogue> {
    val listTv: MutableList<FilmCatalogue> = mutableListOf()

    tvShows?.forEach {
        listTv.add(
            FilmCatalogue(
                it.id,
                it.poster_path,
                it.name,
                it.overview,
                it.vote_average,
                it.first_air_date
            )
        )
    }
    return listTv
}