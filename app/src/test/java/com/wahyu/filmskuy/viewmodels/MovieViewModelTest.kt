package com.wahyu.filmskuy.viewmodels

import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by wahyu_septiadi on 27, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovies() {
        val movies = viewModel.loadMovies()
        Assert.assertNotNull(movies)
        Assert.assertEquals(10, movies.value?.size)
    }
}