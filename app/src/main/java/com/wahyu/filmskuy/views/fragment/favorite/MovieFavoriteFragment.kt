package com.wahyu.filmskuy.views.fragment.favorite

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.adapter.favorite.MovieFavoriteAdapter
import com.wahyu.filmskuy.utils.gone
import com.wahyu.filmskuy.utils.visible
import com.wahyu.filmskuy.viewmodels.local.MovieFavoriteViewModel
import kotlinx.android.synthetic.main.fragment_movie_favorite.*

class MovieFavoriteFragment : Fragment() {
    private lateinit var movieFavoriteViewModel: MovieFavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieFavoriteViewModel = MovieFavoriteViewModel((inflater.context))
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

            val movieFavoriteAdapter = MovieFavoriteAdapter()

            movieFavoriteViewModel.getOnlyMovieFavorite().observe(viewLifecycleOwner) {
                if (it != null && it.isNotEmpty()) {
                    progressMovieFavorite.gone()
                    Log.d("MovieDataFavorite", "$it")
                    movieFavoriteAdapter.setFilms(it)
                } else{
                    movieFavoriteAdapter.setFilms(it)
                    movieFavoriteNotFound.visible()
                    progressMovieFavorite.gone()
                }
            }

            val orientation = resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                rvFavMovie.setHasFixedSize(true)
                rvFavMovie.layoutManager =
                    GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false)
                rvFavMovie.adapter = movieFavoriteAdapter
            } else {
                rvFavMovie.setHasFixedSize(true)
                rvFavMovie.layoutManager =
                    GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                rvFavMovie.adapter = movieFavoriteAdapter
            }
        }
    }
}