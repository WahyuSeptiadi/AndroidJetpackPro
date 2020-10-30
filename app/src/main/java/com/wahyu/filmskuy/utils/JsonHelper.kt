package com.wahyu.filmskuy.utils

import com.wahyu.filmskuy.data.response.MovieResult
import com.wahyu.filmskuy.data.response.TvShowResult
import com.wahyu.filmskuy.models.MovieCatalogue

/**
 * Created by wahyu_septiadi on 29, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

fun getMovieMapper(movies: List<MovieResult>?): MutableList<MovieCatalogue> {
    val listMovie: MutableList<MovieCatalogue> = mutableListOf()

    movies?.forEach {
        listMovie.add(
            MovieCatalogue(
                it.id,
                it.poster_path,
                it.title,
                it.overview,
                it.vote_average,
                it.release_date
            )
        )
    }
    return listMovie
}

fun getTvShowMapper(tvShows: List<TvShowResult>?): MutableList<MovieCatalogue> {
    val listTv: MutableList<MovieCatalogue> = mutableListOf()

    tvShows?.forEach {
        listTv.add(
            MovieCatalogue(
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