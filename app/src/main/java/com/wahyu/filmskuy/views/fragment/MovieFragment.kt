package com.wahyu.filmskuy.views.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.adapter.FilmAdapter
import com.wahyu.filmskuy.utils.gone
import com.wahyu.filmskuy.utils.hideKeyboard
import com.wahyu.filmskuy.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[MovieViewModel::class.java]

            val filmAdapter = FilmAdapter()
            viewModel.movies?.observe(viewLifecycleOwner) {
                filmAdapter.setFilm(it)
            }

            val orientation = resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                rvMovie.layoutManager =
                    GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false)
                rvMovie.setHasFixedSize(true)
                rvMovie.adapter = filmAdapter
            } else {
                rvMovie.layoutManager =
                    GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                rvMovie.setHasFixedSize(true)
                rvMovie.adapter = filmAdapter
            }

            progressMovie.gone()

            etSearchMovie.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchMovie()
                    hideKeyboard()
                    return@setOnEditorActionListener true
                }
                false
            }
        }
    }

    private fun searchMovie() {
        Toast.makeText(context, etSearchMovie.text, Toast.LENGTH_SHORT).show()
        etSearchMovie.text.clear()
    }
}