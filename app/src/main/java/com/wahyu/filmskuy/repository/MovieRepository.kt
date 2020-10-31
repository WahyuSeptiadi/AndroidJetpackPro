package com.wahyu.filmskuy.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wahyu.filmskuy.data.network.ApiClient
import com.wahyu.filmskuy.data.response.MovieResponse
import com.wahyu.filmskuy.models.FilmCatalogue
import com.wahyu.filmskuy.utils.getMovieMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieRepository {

    private var listItems = ArrayList<FilmCatalogue>()
    private val listMovies = MutableLiveData<ArrayList<FilmCatalogue>>()

    private fun getMoviesMapper(): ArrayList<FilmCatalogue> {
        ApiClient.create().getMovie().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movie = response.body() as MovieResponse
                val movieResult = movie.results
                Log.d("MovieResult", "$movieResult")

                listItems = getMovieMapper(movieResult) as ArrayList<FilmCatalogue>
                Log.d("MovieResultMapper", "$listItems")
                listMovies.postValue(listItems)
            }

            override fun onFailure(call: Call<MovieResponse>, throwable: Throwable) {
                Log.d("MovieResultFailed", "onFailure $throwable")
            }
        })
        return listItems
    }

    private fun searchMovie(title: String) {
        ApiClient.create().searchMovie(title).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movie = response.body() as MovieResponse

                val movieResult = movie.results
                Log.d("SearchMovieResultMapper", "$movieResult")
                listItems = getMovieMapper(movieResult) as ArrayList<FilmCatalogue>
                listMovies.postValue(listItems)
            }

            override fun onFailure(call: Call<MovieResponse>, throwable: Throwable) {
                Log.d("SearchMovieResultFailed", "onFailure $throwable")
            }
        })
    }

    fun getAllDataMovies(): LiveData<ArrayList<FilmCatalogue>> {
        getMoviesMapper()
        return listMovies
    }

    fun setTitleSearchMovie(title: String): LiveData<ArrayList<FilmCatalogue>> {
        searchMovie(title)
        return listMovies
    }

}