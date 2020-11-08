package com.wahyu.filmskuy.views.fragment.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.adapter.favorite.MovieFavoriteAdapter
import com.wahyu.filmskuy.data.local.entity.MovieEntity
import com.wahyu.filmskuy.utils.gone
import com.wahyu.filmskuy.viewmodels.local.MovieFavoriteViewModel
import kotlinx.android.synthetic.main.fragment_movie_favorite.*

class MovieFavoriteFragment : Fragment() {
    private lateinit var movieFavoriteViewModel: MovieFavoriteViewModel
    private lateinit var movieFavoriteAdapter: MovieFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieFavoriteViewModel = MovieFavoriteViewModel(inflater.context)
        movieFavoriteAdapter = MovieFavoriteAdapter()

        return inflater.inflate(R.layout.fragment_movie_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
//            val movieFavoriteAdapter = MovieFavoriteAdapter()

            movieFavoriteViewModel.getAll().observe(viewLifecycleOwner, movieObserver)

//            movieFavoriteViewModel.getAll().observe(viewLifecycleOwner) {
//                progressMovieFavorite.gone()
//                movieFavoriteAdapter.submitList(it)
//                movieFavoriteAdapter.notifyDataSetChanged()
//            }

            rvFavMovie.layoutManager = LinearLayoutManager(context)
            rvFavMovie.setHasFixedSize(true)
            rvFavMovie.adapter = movieFavoriteAdapter


//            val orientation = resources.configuration.orientation
//            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                rvFavMovie.setHasFixedSize(true)
//                rvFavMovie.layoutManager =
//                    GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false)
//                rvFavMovie.adapter = movieFavoriteAdapter
//            } else {
//                rvFavMovie.setHasFixedSize(true)
//                rvFavMovie.layoutManager =
//                    GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
//                rvFavMovie.adapter = movieFavoriteAdapter
//            }
        }
    }

    private val movieObserver = Observer<PagedList<MovieEntity>> { movieList ->
        if (movieList.isNotEmpty()) {
            progressMovieFavorite.gone()
            movieFavoriteAdapter.submitList(movieList)
            movieFavoriteAdapter.notifyDataSetChanged()
        }
    }
}