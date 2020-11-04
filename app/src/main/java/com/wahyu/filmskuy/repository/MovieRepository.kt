package com.wahyu.filmskuy.repository

import android.os.Handler
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.wahyu.filmskuy.data.network.ApiClient
import com.wahyu.filmskuy.data.response.MovieResponse
import com.wahyu.filmskuy.data.response.MovieResult
import com.wahyu.filmskuy.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieRepository {

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 5000
    }

    @Suppress("DEPRECATION")
    private val handler = Handler()

    fun getAllMovies(): MutableLiveData<MutableList<MovieResult>> {
        val listData: MutableLiveData<MutableList<MovieResult>> = MutableLiveData()

        ApiClient.create().getMovie().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    EspressoIdlingResource.increment()
                    handler.postDelayed({
                        listData.value = response.body()?.results as MutableList<MovieResult>?
                        EspressoIdlingResource.decrement()
                    }, SERVICE_LATENCY_IN_MILLIS)
                } else {
                    Log.e("MovieIsNotSuccessful", response.message())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("MoviesFailure", t.message.toString())
            }
        })

        return listData
    }

    fun searchMovie(title: String): MutableLiveData<MutableList<MovieResult>> {
        val listData: MutableLiveData<MutableList<MovieResult>> = MutableLiveData()

        ApiClient.create().searchMovie(title).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    listData.value = response.body()?.results as MutableList<MovieResult>?
                } else {
                    Log.e("MovieIsNotSuccessful", response.message())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("MoviesFailure", t.message.toString())
            }
        })

        return listData
    }
}