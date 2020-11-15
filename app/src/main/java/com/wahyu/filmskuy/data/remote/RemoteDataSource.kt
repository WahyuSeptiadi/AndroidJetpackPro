package com.wahyu.filmskuy.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wahyu.filmskuy.data.remote.models.MovieResponse
import com.wahyu.filmskuy.data.remote.models.MovieResult
import com.wahyu.filmskuy.data.remote.network.ApiServices
import com.wahyu.filmskuy.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by wahyu_septiadi on 14, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class RemoteDataSource private constructor(private val apiService: ApiServices) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiServices): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiService)
            }
    }

//    fun getAllDataMovie(): LiveData<ApiResponse<MovieResponse>> {
//        val resultMovie = MutableLiveData<ApiResponse<MovieResponse>>()
//
//        EspressoIdlingResource.increment()
//
//        if (!EspressoIdlingResource.getIdlingResource.isIdleNow) {
//            resultMovie.value = ApiResponse.success(apiService.getAllMovie())
//            EspressoIdlingResource.decrement()
//        }
//
//        return resultMovie
//    }

    fun getAllMovies(): LiveData<MutableList<MovieResult>> {
        val listData: MutableLiveData<MutableList<MovieResult>> = MutableLiveData()

        EspressoIdlingResource.increment()
        apiService.getMovie().enqueue(object : Callback<MovieResponse> {
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
}