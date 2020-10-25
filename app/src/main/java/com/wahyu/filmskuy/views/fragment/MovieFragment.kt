package com.wahyu.filmskuy.views.fragment

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.adapter.MovieAdapter
import com.wahyu.filmskuy.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
            val movies = viewModel.getMovies()

            val academyAdapter = MovieAdapter()
            academyAdapter.setMovies(movies)

            val orientation = resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                rvMovie.layoutManager = GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false)
                rvMovie.setHasFixedSize(true)
                rvMovie.adapter = academyAdapter
            } else {
                rvMovie.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                rvMovie.setHasFixedSize(true)
                rvMovie.adapter = academyAdapter
            }

            progressMovie.visibility = View.GONE
        }
    }
}