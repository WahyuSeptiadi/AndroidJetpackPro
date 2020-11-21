package com.wahyu.filmskuy.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.Mock
import com.nhaarman.mockitokotlin2.verify
import com.wahyu.filmskuy.data.remote.models.MovieResult
import com.wahyu.filmskuy.data.remote.network.ApiClient
import com.wahyu.filmskuy.repository.MovieCatalogueRepository
import com.wahyu.filmskuy.viewmodels.remote.MovieViewModel
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
    private val movieCatalogueRepository: MovieCatalogueRepository =
        mock(MovieCatalogueRepository::class.java)

    @Mock
    private lateinit var observer: Observer<List<MovieResult>>

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(movieCatalogueRepository)
    }

    @Test
    fun getMovies() {
        // pagination test has not been implemented
        val dataDummy = ApiClient.create().getMovie().execute().body()?.results
        val dataList = MutableLiveData<MutableList<MovieResult>>()
        dataList.value = dataDummy as MutableList<MovieResult>?

        Assert.assertNotNull(dataList)
        `when`(movieViewModel.getAllMovieForTest()).thenReturn(dataList)

        movieViewModel.getAllMovieForTest().observeForever(observer)
        verify(observer).onChanged(dataDummy)

        Assert.assertNotNull(movieViewModel.getAllMovieForTest())
        Assert.assertEquals(movieViewModel.getAllMovieForTest(), dataList)
    }
}
