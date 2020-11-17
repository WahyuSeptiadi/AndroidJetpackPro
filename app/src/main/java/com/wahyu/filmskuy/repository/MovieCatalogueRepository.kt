package com.wahyu.filmskuy.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.data.local.entity.TvShowEntity
import com.wahyu.filmskuy.data.local.room.MovieCatalogueDao
import com.wahyu.filmskuy.data.local.room.MovieCatalogueDatabase
import com.wahyu.filmskuy.data.remote.models.MovieResponse
import com.wahyu.filmskuy.data.remote.models.MovieResult
import com.wahyu.filmskuy.data.remote.models.TvShowResponse
import com.wahyu.filmskuy.data.remote.models.TvShowResult
import com.wahyu.filmskuy.data.remote.network.ApiClient
import com.wahyu.filmskuy.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

/**
 * Created by wahyu_septiadi on 16, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

class MovieCatalogueRepository(val context: Context) {
    private val movieCatalogueDao: MovieCatalogueDao
    private val executorService = Executors.newSingleThreadExecutor()

    init {
        val db = MovieCatalogueDatabase.getInstance(context)
        movieCatalogueDao = db.filmCatalogueDao()
    }

    fun getOnlyMovieFavorite(): LiveData<List<MovieEntity>> {
        return movieCatalogueDao.getAllMovieFavorite()
    }

    fun getAllMoviePopular(): LiveData<List<MovieEntity>> {
        getAllMovieFromAPI()
        return movieCatalogueDao.getAllMoviePopular()
    }

    fun getSearchMovieByTitle(title: String): LiveData<List<MovieEntity>> {
        searchMovieFromAPI(title)
        return movieCatalogueDao.getSearchMovieByTitle(title)
    }

    fun updateMovieFavorite(movieEntity: MovieEntity) {
        executorService.execute {
            try {
                movieCatalogueDao.updateMovieDB(movieEntity)
            } catch (e: Exception) {
                Log.e("INSERT FAILED", e.message.toString())
            }
        }
    }

    fun insertAllMovieFromAPI(movieEntity: ArrayList<MovieEntity>) {
        executorService.execute {
            try {
                movieCatalogueDao.insertAllMovieFromAPI(movieEntity)
            } catch (e: Exception) {
                Log.e("INSERT FAILED", e.message.toString())
            }
        }
    }

    private fun getAllMovieFromAPI(): MutableLiveData<MutableList<MovieResult>> {
        val listData: MutableLiveData<MutableList<MovieResult>> = MutableLiveData()

        EspressoIdlingResource.increment()
        ApiClient.create().getMovie().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    if (!EspressoIdlingResource.getIdlingResource.isIdleNow) {
                        listData.value = response.body()?.results as MutableList<MovieResult>?

                        val movieList = ArrayList<MovieEntity>()
                        for (data in listData.value!!) {
                            val movie = MovieEntity(
                                popular = true,
                                favorite = false,
                                id = data.id,
                                overview = data.overview,
                                posterPath = data.posterPath,
                                releaseDate = data.releaseDate,
                                title = data.title,
                                voteAverage = data.voteAverage
                            )
                            movieList.add(movie)
                        }
                        insertAllMovieFromAPI(movieList)

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

    private fun searchMovieFromAPI(title: String): MutableLiveData<MutableList<MovieResult>> {
        val listData: MutableLiveData<MutableList<MovieResult>> = MutableLiveData()

        EspressoIdlingResource.increment()
        ApiClient.create().searchMovie(title).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    if (!EspressoIdlingResource.getIdlingResource.isIdleNow) {
                        listData.value = response.body()?.results as MutableList<MovieResult>?

                        val movieList = ArrayList<MovieEntity>()
                        for (data in listData.value!!) {
                            val movie = MovieEntity(
                                popular = false,
                                favorite = false,
                                id = data.id,
                                overview = data.overview,
                                posterPath = data.posterPath,
                                releaseDate = data.releaseDate,
                                title = data.title,
                                voteAverage = data.voteAverage
                            )
                            movieList.add(movie)
                        }
                        insertAllMovieFromAPI(movieList)

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

    fun getOnlyTvShowFavorite(): LiveData<List<TvShowEntity>> {
        return movieCatalogueDao.getAllTvShowFavorite()
    }

    fun getAllTvShowPopular(): LiveData<List<TvShowEntity>> {
        getAllTvShowsFromAPI()
        return movieCatalogueDao.getAllTvShowPopular()
    }

    fun getSearchTvShowByName(name: String): LiveData<List<TvShowEntity>> {
        searchTvShowFromAPI(name)
        return movieCatalogueDao.getSearchTvShowByName(name)
    }

    fun updateTvShowFavorite(movieEntity: TvShowEntity) {
        executorService.execute {
            try {
                movieCatalogueDao.updateTvShowDB(movieEntity)
            } catch (e: Exception) {
                Log.e("INSERT FAILED", e.message.toString())
            }
        }
    }

    fun insertAllTvShowFromAPI(tvShowEntity: ArrayList<TvShowEntity>) {
        executorService.execute {
            try {
                movieCatalogueDao.insertAllTvShowFromAPI(tvShowEntity)
            } catch (e: Exception) {
                Log.e("INSERT FAILED", e.message.toString())
            }
        }
    }

    private fun getAllTvShowsFromAPI(): MutableLiveData<MutableList<TvShowResult>> {
        val listData: MutableLiveData<MutableList<TvShowResult>> = MutableLiveData()

        EspressoIdlingResource.increment()
        ApiClient.create().getTvShow().enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    if (!EspressoIdlingResource.getIdlingResource.isIdleNow) {
                        listData.value = response.body()?.results as MutableList<TvShowResult>?

                        val tvShowList = ArrayList<TvShowEntity>()
                        for (data in listData.value!!) {
                            val tvShow = TvShowEntity(
                                popular = true,
                                favorite = false,
                                firstAirDate = data.firstAirDate,
                                id = data.id,
                                name = data.name,
                                overview = data.overview,
                                posterPath = data.posterPath,
                                voteAverage = data.voteAverage
                            )
                            tvShowList.add(tvShow)
                        }
                        insertAllTvShowFromAPI(tvShowList)

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

    private fun searchTvShowFromAPI(title: String): MutableLiveData<MutableList<TvShowResult>> {
        val listData: MutableLiveData<MutableList<TvShowResult>> = MutableLiveData()

        EspressoIdlingResource.increment()
        ApiClient.create().searchTvShow(title).enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    if (!EspressoIdlingResource.getIdlingResource.isIdleNow) {
                        listData.value = response.body()?.results as MutableList<TvShowResult>?
                        val tvShowList = ArrayList<TvShowEntity>()
                        for (data in listData.value!!) {
                            val tvShow = TvShowEntity(
                                popular = false,
                                favorite = false,
                                firstAirDate = data.firstAirDate,
                                id = data.id,
                                name = data.name,
                                overview = data.overview,
                                posterPath = data.posterPath,
                                voteAverage = data.voteAverage
                            )
                            tvShowList.add(tvShow)
                        }
                        insertAllTvShowFromAPI(tvShowList)

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
}