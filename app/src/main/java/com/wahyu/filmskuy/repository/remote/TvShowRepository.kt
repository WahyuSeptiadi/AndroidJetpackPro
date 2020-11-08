package com.wahyu.filmskuy.repository.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.wahyu.filmskuy.data.remote.network.ApiClient
import com.wahyu.filmskuy.data.remote.response.TvShowResponse
import com.wahyu.filmskuy.data.remote.response.TvShowResult
import com.wahyu.filmskuy.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class TvShowRepository {
    fun getAllTvShows(): MutableLiveData<MutableList<TvShowResult>> {
        val listData: MutableLiveData<MutableList<TvShowResult>> = MutableLiveData()

        EspressoIdlingResource.increment()
        ApiClient.create().getTvShow().enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                if (response.isSuccessful) {
                    if (!EspressoIdlingResource.getIdlingResource.isIdleNow) {
                        listData.value = response.body()?.results as MutableList<TvShowResult>?
                        EspressoIdlingResource.decrement()
                    }
                } else {
                    Log.e("TvShowIsNotSuccessful", response.message())
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e("TvShowsFailure", t.message.toString())
            }
        })

        return listData
    }

    fun searchTvShow(title: String): MutableLiveData<MutableList<TvShowResult>> {
        val listData: MutableLiveData<MutableList<TvShowResult>> = MutableLiveData()

        ApiClient.create().searchTvShow(title).enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    listData.value = response.body()?.results as MutableList<TvShowResult>?
                } else {
                    Log.e("TvShowIsNotSuccessful", response.message())
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e("TvShowsFailure", t.message.toString())
            }
        })

        return listData
    }
}