package com.wahyu.filmskuy.views.fragment

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.adapter.FilmAdapter
import com.wahyu.filmskuy.utils.gone
import com.wahyu.filmskuy.utils.hideKeyboard
import com.wahyu.filmskuy.utils.toast
import com.wahyu.filmskuy.utils.visible
import com.wahyu.filmskuy.viewmodels.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[TvShowViewModel::class.java]

            val filmAdapter = FilmAdapter()
            viewModel.loadTvShows().observe(viewLifecycleOwner) {
                if (it != null) {
                    progressTvShow.gone()
                }
                filmAdapter.setFilm(it)
            }

            etSearchTvShow.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val titleKey = etSearchTvShow.text

                    progressTvShow.visible()
                    txtTvShowNotFound.gone()
                    if (titleKey.isNotEmpty()) {
                        viewModel.searchTvShow(titleKey.toString()).observe(viewLifecycleOwner) {
                            if (it.isNotEmpty()) {
                                txtTvShowNotFound.gone()
                            } else {
                                txtTvShowNotFound.visible()
                            }
                            progressTvShow.gone()
                            filmAdapter.setFilm(it)
                        }
                    } else {
                        toast("Please, enter keyword!")
                        progressTvShow.gone()
                    }
                    titleKey.clear()
                    hideKeyboard()
                    return@setOnEditorActionListener true
                }
                false
            }

            val orientation = resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                rvTvShow.layoutManager =
                    GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false)
                rvTvShow.setHasFixedSize(true)
                rvTvShow.adapter = filmAdapter
            } else {
                rvTvShow.layoutManager =
                    GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                rvTvShow.setHasFixedSize(true)
                rvTvShow.adapter = filmAdapter
            }
        }
    }
}