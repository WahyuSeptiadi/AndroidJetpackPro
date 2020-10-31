package com.wahyu.filmskuy.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wahyu.filmskuy.data.network.ApiClient
import com.wahyu.filmskuy.data.response.TvShowResponse
import com.wahyu.filmskuy.models.FilmCatalogue
import com.wahyu.filmskuy.utils.getTvShowMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class TvShowRepository {
    private var listItems = ArrayList<FilmCatalogue>()
    private val listTvShows = MutableLiveData<ArrayList<FilmCatalogue>>()

    private fun getTvShowsMapper(): ArrayList<FilmCatalogue> {
        ApiClient.create().getTvShow().enqueue(object : retrofit2.Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                val tvShow = response.body() as TvShowResponse
                val tvShowResult = tvShow.results
                Log.d("MovieResult", "$tvShowResult")

                listItems = getTvShowMapper(tvShowResult) as ArrayList<FilmCatalogue>
                Log.d("MovieResultMapper", "$listItems")
                listTvShows.postValue(listItems)
            }

            override fun onFailure(call: Call<TvShowResponse>, throwable: Throwable) {
                Log.d("MovieResultFailed", "onFailure $throwable")
            }
        })
        return listItems
    }

    private fun searchTvShow(title: String) {
        ApiClient.create().searchTvShow(title).enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                val tvShow = response.body() as TvShowResponse

                val tvShowResult = tvShow.results
                Log.d("SearchMovieResultMapper", "$tvShowResult")
                listItems = getTvShowMapper(tvShowResult) as ArrayList<FilmCatalogue>
                listTvShows.postValue(listItems)
            }

            override fun onFailure(call: Call<TvShowResponse>, throwable: Throwable) {
                Log.d("SearchMovieResultFailed", "onFailure $throwable")
            }
        })
    }

    fun getAllDataTvShows(): LiveData<ArrayList<FilmCatalogue>> {
        getTvShowsMapper()
        return listTvShows
    }

    fun setTitleSearchTvShow(title: String): LiveData<ArrayList<FilmCatalogue>> {
        searchTvShow(title)
        return listTvShows
    }
}