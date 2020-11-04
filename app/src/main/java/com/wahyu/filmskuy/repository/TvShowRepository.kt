package com.wahyu.filmskuy.repository

import android.os.Handler
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.wahyu.filmskuy.data.network.ApiClient
import com.wahyu.filmskuy.data.response.TvShowResponse
import com.wahyu.filmskuy.data.response.TvShowResult
import com.wahyu.filmskuy.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class TvShowRepository {

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 5000
    }

    private val handler = Handler()

    fun getAllTvShows(): MutableLiveData<MutableList<TvShowResult>> {
        val listData: MutableLiveData<MutableList<TvShowResult>> = MutableLiveData()

        ApiClient.create().getTvShow().enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                if (response.isSuccessful) {
                    EspressoIdlingResource.increment()
                    handler.postDelayed({
                        listData.value = response.body()?.results as MutableList<TvShowResult>?
                        EspressoIdlingResource.decrement()
                    }, SERVICE_LATENCY_IN_MILLIS)
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