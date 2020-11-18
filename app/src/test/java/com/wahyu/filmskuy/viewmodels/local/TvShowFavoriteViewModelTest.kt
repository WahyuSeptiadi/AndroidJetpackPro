package com.wahyu.filmskuy.viewmodels.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.wahyu.filmskuy.data.local.entity.TvShowEntity
import com.wahyu.filmskuy.repository.MovieCatalogueRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by wahyu_septiadi on 18, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

@RunWith(MockitoJUnitRunner::class)
class TvShowFavoriteViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var tvShowFavoriteViewModel: TvShowFavoriteViewModel

    @Mock
    private val movieCatalogueRepository: MovieCatalogueRepository =
        Mockito.mock(MovieCatalogueRepository::class.java)

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        tvShowFavoriteViewModel = TvShowFavoriteViewModel(movieCatalogueRepository)
    }

    @Test
    fun getTvShowsFavoritePagedList() {
        val dataDummy = pagedList
        val dataList = MutableLiveData<PagedList<TvShowEntity>>()
        dataList.value = dataDummy

        Assert.assertNotNull(dataList)
        Mockito.`when`(tvShowFavoriteViewModel.getOnlyTvShowFavorite()).thenReturn(dataList)

        tvShowFavoriteViewModel.getOnlyTvShowFavorite().observeForever(observer)
        verify(observer).onChanged(dataDummy)

        Assert.assertNotNull(tvShowFavoriteViewModel.getOnlyTvShowFavorite())
        Assert.assertEquals(tvShowFavoriteViewModel.getOnlyTvShowFavorite(), dataList)
    }
}