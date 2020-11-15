package com.wahyu.filmskuy.di

import android.content.Context
import com.wahyu.filmskuy.data.MovieCatalogueRepository
import com.wahyu.filmskuy.data.local.LocalDataSource
import com.wahyu.filmskuy.data.local.room.MovieCatalogueDatabase
import com.wahyu.filmskuy.data.remote.RemoteDataSource
import com.wahyu.filmskuy.data.remote.network.ApiClient
import com.wahyu.filmskuy.utils.AppExecutors

/**
 * Created by wahyu_septiadi on 15, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

object Injection {
    fun provideRepository(context: Context): MovieCatalogueRepository {
        val apiClient = ApiClient.create()
        val database = MovieCatalogueDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(apiClient)
        val localDataSource = LocalDataSource.getInstance(database.filmCatalogueDao())
        val appExecutors = AppExecutors()

        return MovieCatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}