package com.wahyu.filmskuy.viewmodels.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.wahyu.filmskuy.data.local.entity.TvShowEntity
import com.wahyu.filmskuy.repository.MovieCatalogueRepository
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
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel(movieCatalogueRepository)
    }

    @Test
    fun getTvShowsPopularPagedList() {
        val dataDummy = pagedList
        val dataList = MutableLiveData<PagedList<TvShowEntity>>()
        dataList.value = dataDummy

        Assert.assertNotNull(dataList)
        `when`(tvShowViewModel.getAllTvShowPopular()).thenReturn(dataList)

        tvShowViewModel.getAllTvShowPopular().observeForever(observer)
        verify(observer).onChanged(dataDummy)

        Assert.assertNotNull(tvShowViewModel.getAllTvShowPopular())
        Assert.assertEquals(tvShowViewModel.getAllTvShowPopular(), dataList)
    }
}