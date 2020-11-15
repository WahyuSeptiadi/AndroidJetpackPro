package com.wahyu.filmskuy.views.fragment.main

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.adapter.main.MovieListAdapter
import com.wahyu.filmskuy.utils.gone
import com.wahyu.filmskuy.utils.hideKeyboard
import com.wahyu.filmskuy.utils.toast
import com.wahyu.filmskuy.utils.visible
import com.wahyu.filmskuy.viewmodels.MovieCatalogueViewModel
import com.wahyu.filmskuy.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

//    private lateinit var movieViewModel: MovieViewModel

//    private lateinit var movieCatalogueViewModel: MovieCatalogueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
//        movieViewModel = MovieViewModel(MovieRepository())

//        movieCatalogueViewModel = MovieCatalogueViewModel(FilmCatalogueRepository())
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

            val movieListAdapter = MovieListAdapter()

            val factory = ViewModelFactory.getInstance(requireActivity())
            val movieViewModel =
                ViewModelProvider(this, factory)[MovieCatalogueViewModel::class.java]

            movieViewModel.getAllMovies().observe(viewLifecycleOwner) {
                if (it != null) {
                    progressMovie.gone()
                }
                movieListAdapter.setFilm(it.data)
            }

//            etSearchMovie.setOnEditorActionListener { _, actionId, _ ->
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    val titleKey = etSearchMovie.text
//
//                    progressMovie.visible()
//                    txtMovieNotFound.gone()
//
//                    if (titleKey.isNotEmpty()) {
//                        movieViewModel.setMovie(titleKey.toString()).observe(viewLifecycleOwner) {
//                            if (it.isNotEmpty()) {
//                                txtMovieNotFound.gone()
//                            } else {
//                                txtMovieNotFound.visible()
//                            }
//                            progressMovie.gone()
//                            movieListAdapter.setFilm(it)
//                        }
//                    } else {
//                        toast("Please, enter keyword!")
//                        progressMovie.gone()
//                    }
//
//                    titleKey.clear()
//                    hideKeyboard()
//                    return@setOnEditorActionListener true
//                }
//                false
//            }

            val orientation = resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                rvMovie.layoutManager =
                    GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false)
                rvMovie.setHasFixedSize(true)
                rvMovie.adapter = movieListAdapter
            } else {
                rvMovie.layoutManager =
                    GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                rvMovie.setHasFixedSize(true)
                rvMovie.adapter = movieListAdapter
            }
        }
    }
}