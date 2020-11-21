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
import com.wahyu.filmskuy.adapter.favorite.TvShowFavoriteAdapter
import com.wahyu.filmskuy.repository.MovieCatalogueRepository
import com.wahyu.filmskuy.utils.gone
import com.wahyu.filmskuy.utils.visible
import com.wahyu.filmskuy.viewmodels.local.TvShowFavoriteViewModel
import kotlinx.android.synthetic.main.fragment_tv_show_favorite.*

class TvShowFavoriteFragment : Fragment() {
    private lateinit var tvShowFavoriteViewModel: TvShowFavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tvShowFavoriteViewModel =
            TvShowFavoriteViewModel(MovieCatalogueRepository(inflater.context))
        return inflater.inflate(R.layout.fragment_tv_show_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val tvShowFavoriteAdapter = TvShowFavoriteAdapter()

            tvShowFavoriteViewModel.getOnlyTvShowFavorite().observe(viewLifecycleOwner) {
                if (it != null && it.isNotEmpty()) {
                    progressTvShowFavorite.gone()
                    Log.d("TvDataFavorite", "$it")
                } else {
                    progressTvShowFavorite.gone()
                    tvShowFavoriteNotFound.visible()
                }
                tvShowFavoriteAdapter.setFilms(it)
            }

            val orientation = resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                rvFavTvShow.layoutManager =
                    GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false)
            } else {
                rvFavTvShow.layoutManager =
                    GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            }
            rvFavTvShow.setHasFixedSize(true)
            rvFavTvShow.adapter = tvShowFavoriteAdapter
        }
    }
}