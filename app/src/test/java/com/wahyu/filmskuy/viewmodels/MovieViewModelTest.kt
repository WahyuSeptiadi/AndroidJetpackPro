package com.wahyu.filmskuy.viewmodels

import com.wahyu.filmskuy.models.FilmCatalogue
import com.wahyu.filmskuy.repository.MovieRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by wahyu_septiadi on 27, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: androidx.lifecycle.Observer<List<FilmCatalogue>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovies() {
        val dummyMovies = movieRepository.getAllDataMovies()

        Mockito.`when`(movieRepository.getAllDataMovies()).thenReturn(dummyMovies)
        val moviesEntities = viewModel.loadMovies().value
        Mockito.verify(movieRepository).getAllDataMovies()

        Assert.assertNotNull(moviesEntities)
        Assert.assertEquals(20, moviesEntities?.size)

        viewModel.loadMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(moviesEntities)
    }
}
