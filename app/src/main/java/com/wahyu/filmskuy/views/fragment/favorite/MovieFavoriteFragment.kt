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
import com.wahyu.filmskuy.repository.MovieCatalogueRepository
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
        movieFavoriteViewModel = MovieFavoriteViewModel(MovieCatalogueRepository(inflater.context))
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val movieAdapter = MovieFavoriteAdapter()

            movieFavoriteViewModel.getOnlyMovieFavorite().observe(viewLifecycleOwner) {
                if (it != null && it.isNotEmpty()) {
                    progressMovieFavorite.gone()
                    Log.d("MovieDataFavorite", "$it")
                } else {
                    movieFavoriteNotFound.visible()
                    progressMovieFavorite.gone()
                }
                movieAdapter.setFilms(it)
            }

            val orientation = resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                rvFavMovie.layoutManager =
                    GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false)
            } else {
                rvFavMovie.layoutManager =
                    GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            }
            rvFavMovie.setHasFixedSize(true)
            rvFavMovie.adapter = movieAdapter
        }
    }
}