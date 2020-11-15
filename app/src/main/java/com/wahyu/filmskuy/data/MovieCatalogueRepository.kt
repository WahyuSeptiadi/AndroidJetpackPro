package com.wahyu.filmskuy.data

import androidx.lifecycle.LiveData
import com.wahyu.filmskuy.data.local.LocalDataSource
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.data.remote.RemoteDataSource
import com.wahyu.filmskuy.data.remote.models.MovieResult
import com.wahyu.filmskuy.utils.AppExecutors
import com.wahyu.filmskuy.vo.Resources

/**
 * Created by wahyu_septiadi on 14, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieCatalogueRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieCatalogueDataSource {

    companion object {
        @Volatile
        private var instance: MovieCatalogueRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieCatalogueRepository = instance ?: synchronized(this) {
            instance ?: MovieCatalogueRepository(remoteDataSource, localDataSource, appExecutors)
        }
    }

    override fun getAllMovie(): LiveData<Resources<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResult>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> = localDataSource.getAllMovieDB()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<MutableList<MovieResult>> =
                remoteDataSource.getAllMovies()

            override fun saveCallResult(data: List<MovieResult>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.adult,
                        response.backdropPath,
                        response.id,
                        response.originalLanguage,
                        response.originalTitle,
                        response.overview,
                        response.popularity,
                        response.posterPath,
                        response.releaseDate,
                        response.title,
                        response.video,
                        response.voteAverage,
                        response.voteCount
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovie(movieList)
            }

        }.asLiveData()
    }

    override fun searchMovie(title: String): LiveData<Resources<List<MovieEntity>>> {
        TODO("Not yet implemented")
    }

}