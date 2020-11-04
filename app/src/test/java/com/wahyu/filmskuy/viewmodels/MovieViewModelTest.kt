package com.wahyu.filmskuy.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.wahyu.filmskuy.models.FilmCatalogue
import com.wahyu.filmskuy.repository.MovieRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.Mock
import com.nhaarman.mockitokotlin2.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by wahyu_septiadi on 27, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel

    @Mock
    private val movieRepository: MovieRepository = mock(MovieRepository::class.java)

    @Mock
    private lateinit var observer: Observer<List<FilmCatalogue>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovies() {
        val listMovie = viewModel.loadMovies().value
        val liveDataMovie = MutableLiveData<List<FilmCatalogue>>()

        liveDataMovie.value = listMovie

        `when`(movieRepository.getAllDataMovies()).thenReturn(liveDataMovie)
        val moviesEntities = viewModel.loadMovies().value
        verify(movieRepository).getAllDataMovies()
        Assert.assertNotNull(moviesEntities)

        viewModel.loadMovies().observeForever(observer)
        verify(observer).onChanged(listMovie)
    }
}
