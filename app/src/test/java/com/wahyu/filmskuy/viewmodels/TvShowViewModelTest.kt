package com.wahyu.filmskuy.viewmodels

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShows() {
        val filmModels = viewModel.getTvShows()
        assertNotNull(filmModels)
        assertEquals(10, filmModels.size)
    }
}