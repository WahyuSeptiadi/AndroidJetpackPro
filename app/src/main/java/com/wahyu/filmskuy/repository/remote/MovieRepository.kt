package com.wahyu.filmskuy.repository.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.wahyu.filmskuy.data.remote.network.ApiClient
import com.wahyu.filmskuy.data.remote.response.MovieResponse
import com.wahyu.filmskuy.data.remote.response.MovieResult
import com.wahyu.filmskuy.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieRepository {
    fun getAllMovies(): MutableLiveData<MutableList<MovieResult>> {
        val listData: MutableLiveData<MutableList<MovieResult>> = MutableLiveData()

        EspressoIdlingResource.increment()
        ApiClient.create().getMovie().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    if (!EspressoIdlingResource.getIdlingResource.isIdleNow) {
                        listData.value = response.body()?.results as MutableList<MovieResult>?
                        EspressoIdlingResource.decrement()
                    }
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