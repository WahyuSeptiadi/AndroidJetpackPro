package com.wahyu.filmskuy.viewmodels.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.Mock
import com.nhaarman.mockitokotlin2.verify
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.repository.MovieCatalogueRepository
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
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(movieCatalogueRepository)
    }

    @Test
    fun getMoviesPopularPagedList() {
        val dataDummy = pagedList
        val dataList = MutableLiveData<PagedList<MovieEntity>>()
        dataList.value = dataDummy

        Assert.assertNotNull(dataList)
        `when`(movieViewModel.getAllMoviePopular()).thenReturn(dataList)

        movieViewModel.getAllMoviePopular().observeForever(observer)
        verify(observer).onChanged(dataDummy)

        Assert.assertNotNull(movieViewModel.getAllMoviePopular())
        Assert.assertEquals(movieViewModel.getAllMoviePopular(), dataList)

        // Expected 10 Actual 0 wkwk (unit testing yang lain juga sama belum di benerin)
        Assert.assertEquals(10, movieViewModel.getAllMoviePopular().value?.size)
    }
}
