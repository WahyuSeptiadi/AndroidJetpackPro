package com.wahyu.filmskuy.data.remote.network

import com.wahyu.filmskuy.data.remote.models.MovieResponse
import com.wahyu.filmskuy.data.remote.models.TvShowResponse
import com.wahyu.filmskuy.utils.API_SEARCH_LAST
import com.wahyu.filmskuy.utils.API_URL_LAST
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by wahyu_septiadi on 29, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

interface ApiServices {
    @GET("3/discover/movie/$API_URL_LAST")
    fun getMovie(): Call<MovieResponse>

    @GET("3/discover/tv/$API_URL_LAST")
    fun getTvShow(): Call<TvShowResponse>

    @GET("3/search/movie$API_SEARCH_LAST")
    fun searchMovie(
        @Query("query") title: String
    ): Call<MovieResponse>

    @GET("3/search/tv$API_SEARCH_LAST")
    fun searchTvShow(
        @Query("query") title: String
    ): Call<TvShowResponse>
}