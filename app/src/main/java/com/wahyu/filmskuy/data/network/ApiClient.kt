package com.wahyu.filmskuy.data.network

import com.wahyu.filmskuy.utils.API_BASE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by wahyu_septiadi on 29, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

object ApiClient {

    fun create(): ApiServices {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiServices::class.java)
    }
}