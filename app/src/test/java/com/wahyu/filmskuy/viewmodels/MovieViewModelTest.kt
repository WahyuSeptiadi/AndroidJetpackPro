package com.wahyu.filmskuy.viewmodels

import org.junit.Assert.*
import org.junit.Test
import org.junit.Before

/**
 * Created by wahyu_septiadi on 25, October 2020.
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
        val movies = viewModel.movies
        assertNotNull(movies)
        assertEquals(10, movies.size)
    }
}