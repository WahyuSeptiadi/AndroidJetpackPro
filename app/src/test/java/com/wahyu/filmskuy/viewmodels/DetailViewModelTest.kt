package com.wahyu.filmskuy.viewmodels

import com.wahyu.filmskuy.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Created by wahyu_septiadi on 26, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class DetailViewModelTest {

    private lateinit var movieModel: DetailViewModel
    private lateinit var tvShowModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[1]
    private val dummyTvShow = DataDummy.generateDummyTvShow()[1]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @Before
    fun setUp() {
        movieModel = DetailViewModel()
        movieModel.setSelectedFilm(movieId)
        tvShowModel = DetailViewModel()
        tvShowModel.setSelectedFilm(tvShowId)
    }

    @Test
    fun getDetailMovie() {
        movieModel.setSelectedFilm(movieId)
        val movieEntity = movieModel.getDetailFilm()
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.image, movieEntity.image)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.rating, movieEntity.rating)
        assertEquals(dummyMovie.release, movieEntity.release)
        assertEquals(dummyMovie.overview, movieEntity.overview)
    }

    @Test
    fun getDetailTvShow() {
        tvShowModel.setSelectedFilm(tvShowId)
        val tvShowEntity = tvShowModel.getDetailFilm()
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.image, tvShowEntity.image)
        assertEquals(dummyTvShow.title, tvShowEntity.title)
        assertEquals(dummyTvShow.rating, tvShowEntity.rating)
        assertEquals(dummyTvShow.release, tvShowEntity.release)
        assertEquals(dummyTvShow.overview, tvShowEntity.overview)
    }
}