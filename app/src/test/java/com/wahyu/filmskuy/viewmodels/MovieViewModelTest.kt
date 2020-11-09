package com.wahyu.filmskuy.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.wahyu.filmskuy.repository.MovieRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.Mock
import com.nhaarman.mockitokotlin2.verify
import com.wahyu.filmskuy.data.network.ApiClient
import com.wahyu.filmskuy.data.response.MovieResult
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by wahyu_septiadi on 27, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieViewModel: MovieViewModel

    @Mock
    private val movieRepository: MovieRepository = mock(MovieRepository::class.java)

    @Mock
    private lateinit var observer: Observer<List<MovieResult>>

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovies() {
        val dataDummy = ApiClient.create().getMovie().execute().body()?.results
        val dataList = MutableLiveData<MutableList<MovieResult>>()
        dataList.value = dataDummy as MutableList<MovieResult>?

        Assert.assertNotNull(dataList)
        `when`(movieViewModel.getMovies()).thenReturn(dataList)

        movieViewModel.getMovies()?.observeForever(observer)
        verify(observer).onChanged(dataDummy)

        Assert.assertNotNull(movieViewModel.getMovies())
        Assert.assertEquals(movieViewModel.getMovies(), dataList)
    }
}
