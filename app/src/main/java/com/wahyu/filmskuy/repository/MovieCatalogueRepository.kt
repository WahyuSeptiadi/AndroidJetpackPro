package com.wahyu.filmskuy.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
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

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(10)
        .setPageSize(10)
        .build()

    init {
        val db = MovieCatalogueDatabase.getInstance(context)
        movieCatalogueDao = db.filmCatalogueDao()
    }

    fun getOnlyMovieFavorite(): LiveData<PagedList<MovieEntity>> {
        return LivePagedListBuilder(movieCatalogueDao.getAllMovieFavorite(), config).build()
    }

    fun getAllMoviePopular(): LiveData<PagedList<MovieEntity>> {
        getAllMovieFromAPI()
        return LivePagedListBuilder(movieCatalogueDao.getAllMoviePopular(), config).build()
    }

    fun getSearchMovieByTitle(title: String): LiveData<PagedList<MovieEntity>> {
        searchMovieFromAPI(title)
        return LivePagedListBuilder(movieCatalogueDao.getSearchMovieByTitle(title), config).build()
    }

    fun updateMovieFavorite(movieEntity: MovieEntity) {
        executorService.execute {
            try {
                movieCatalogueDao.updateMovieDB(movieEntity)
            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    "Update Movie Favorite FAILED!\n${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun insertAllMovieFromAPI(movieEntity: ArrayList<MovieEntity>) {
        executorService.execute {
            try {
                movieCatalogueDao.insertAllMovieFromAPI(movieEntity)
            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    "Insert Movie FAILED!\n${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
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

    fun getOnlyTvShowFavorite(): LiveData<PagedList<TvShowEntity>> {
        return LivePagedListBuilder(movieCatalogueDao.getAllTvShowFavorite(), config).build()
    }

    fun getAllTvShowPopular(): LiveData<PagedList<TvShowEntity>> {
        getAllTvShowsFromAPI()
        return LivePagedListBuilder(movieCatalogueDao.getAllTvShowPopular(), config).build()
    }

    fun getSearchTvShowByName(name: String): LiveData<PagedList<TvShowEntity>> {
        searchTvShowFromAPI(name)
        return LivePagedListBuilder(movieCatalogueDao.getSearchTvShowByName(name), config).build()
    }

    fun updateTvShowFavorite(movieEntity: TvShowEntity) {
        executorService.execute {
            try {
                movieCatalogueDao.updateTvShowDB(movieEntity)
            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    "Update Tv Show Favorite FAILED!\n${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun insertAllTvShowFromAPI(tvShowEntity: ArrayList<TvShowEntity>) {
        executorService.execute {
            try {
                movieCatalogueDao.insertAllTvShowFromAPI(tvShowEntity)
            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    "Insert Tv Show FAILED! ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
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