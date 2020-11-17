package com.wahyu.filmskuy.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.wahyu.filmskuy.data.remote.models.TvShowResult
import com.wahyu.filmskuy.data.remote.network.ApiClient
import com.wahyu.filmskuy.repository.MovieCatalogueRepository
import com.wahyu.filmskuy.viewmodels.remote.TvShowViewModel
import org.junit.Assert
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var tvShowViewModel: TvShowViewModel

    @Mock
    private val movieCatalogueRepository: MovieCatalogueRepository =
        mock(MovieCatalogueRepository::class.java)

    @Mock
    private lateinit var observer: Observer<List<TvShowResult>>

    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel(movieCatalogueRepository)
    }

    @Test
    fun getTvShows() {
        val dataDummy = ApiClient.create().getTvShow().execute().body()?.results
        val dataList = MutableLiveData<MutableList<TvShowResult>>()
        dataList.value = dataDummy as MutableList<TvShowResult>?

        Assert.assertNotNull(dataList)
        `when`(tvShowViewModel.getAllTvShowForTest()).thenReturn(dataList)

        tvShowViewModel.getAllTvShowForTest().observeForever(observer)
        verify(observer).onChanged(dataDummy)

        Assert.assertNotNull(tvShowViewModel.getAllTvShowForTest())
        Assert.assertEquals(tvShowViewModel.getAllTvShowForTest(), dataList)
    }
}