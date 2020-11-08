package com.wahyu.filmskuy.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.wahyu.filmskuy.data.remote.response.TvShowResult
import com.wahyu.filmskuy.repository.remote.TvShowRepository
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
    private val tvShowRepository: TvShowRepository = mock(TvShowRepository::class.java)

    @Mock
    private lateinit var observer: Observer<List<TvShowResult>>

    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel(tvShowRepository)
    }

    @Test
    fun getTvShows() {
        val dataDummy : MutableLiveData<MutableList<TvShowResult>> = MutableLiveData()
        val dataList = mock(MutableList::class.java) as MutableList<TvShowResult>?

        dataDummy.value = dataList

        `when`(tvShowViewModel.getTvShows()).thenReturn(dataDummy)

        tvShowViewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dataList)
        Assert.assertNotNull(tvShowViewModel.getTvShows())
    }
}