package com.wahyu.filmskuy.utils

import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.data.local.entity.TvShowEntity
import java.util.ArrayList

/**
 * Created by wahyu_septiadi on 17, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

object ExpectationDataDummy {
    fun dataDummyMovie(): List<MovieEntity> {

        val movie = ArrayList<MovieEntity>()

        movie.add(
            MovieEntity(
                popular = true,
                favorite = false,
                id = 337401,
                overview = "When the Emperor of China issues a decree that one man per family must serve in the Imperial Chinese Army to defend the country from Huns, Hua Mulan, the eldest daughter of an honored warrior, steps in to take the place of her ailing father. She is spirited, determined and quick on her feet. Disguised as a man by the name of Hua Jun, she is tested every step of the way and must harness her innermost strength and embrace her true potential.",
                posterPath = "/aKx1ARwG55zZ0GpRvU2WrGrCG9o.jpg",
                releaseDate = "2020-09-04",
                title = "Mulan",
                voteAverage = 7.2
            )
        )

        return movie
    }

    fun dataDummyTvShow(): List<TvShowEntity> {

        val tvShow = ArrayList<TvShowEntity>()

        tvShow.add(
            TvShowEntity(
                popular = true,
                favorite = false,
                id = 456,
                overview = "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                posterPath = "/2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg",
                firstAirDate = "1989-12-16",
                name = "The Simpsons",
                voteAverage = 7.7
            )
        )

        return tvShow
    }
}